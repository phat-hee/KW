// main.cpp: 응용 프로그램의 진입점을 정의합니다.
//
#include "stdafx.h"
#include "main.h"

#define MAX_LOADSTRING 100
#define CURRENT_TITLE _T("ASSEMBLE_SOKOBAN") // 제목 표시줄 텍스트입니다.
#define CURRENT_WND_CLASS _T("ASSEMBLE_SOKOBAN") // 기본 창 클래스 이름입니다.

// 이 코드 모듈에 들어 있는 함수의 정방향 선언입니다.
ATOM                MyRegisterClass(HINSTANCE hInstance);
BOOL                InitInstance(HINSTANCE, int);
LRESULT CALLBACK    WndProc(HWND, UINT, WPARAM, LPARAM);
// 사용자 정의 함수

// 사용자 정의 전역변수
HINSTANCE g_hInst;                              // 전역 인스턴스
HWND g_hWnd;									// 전역 핸들
POINT g_ptMouse = { 0L, 0L };

GameLoop* g_gameLoop;							// 게임 루프 객체

//PAINTSTRUCT ps; // 윈도우의 출력 정보를 보관하고 있는 구조체
RECT winRC;
HDC hMainDC;

int APIENTRY wWinMain(_In_ HINSTANCE hInstance,
	_In_opt_ HINSTANCE hPrevInstance,
	_In_ LPWSTR    lpCmdLine,
	_In_ int       nCmdShow)
{
	UNREFERENCED_PARAMETER(hPrevInstance);
	UNREFERENCED_PARAMETER(lpCmdLine);

	// TODO: 여기에 코드를 입력합니다.

	// 전역 문자열을 초기화합니다.
	//LoadStringW(hInstance, IDS_APP_TITLE, szTitle, MAX_LOADSTRING);
	//LoadStringW(hInstance, IDC_ASSEMBLEGAMEPROJECT, szWindowClass, MAX_LOADSTRING);

	// 윈도우 클래스 등록
	MyRegisterClass(hInstance);

	// 게임루프 객체 함수포인터 등록
	g_gameLoop = NewGameLoop();

	// 응용 프로그램 초기화를 수행합니다.
	if (!InitInstance(hInstance, nCmdShow))
	{
		return false;
	}

	if (FAILED(g_gameLoop->Init())) return false;

	UpdateWindow(g_hWnd);

	HACCEL hAccelTable = LoadAccelerators(hInstance, MAKEINTRESOURCE(IDC_ASSEMBLEGAMEPROJECT));

	MSG msg;
	ZeroMemory(&msg, sizeof(MSG)); // 메모리 할당

	// 게임 루프
	while (msg.message != WM_QUIT)
	{
		if (PeekMessage(&msg, NULL, 0, 0, PM_REMOVE)) // PM_REMOVE : 메세지 큐를 읽고 메세지 큐에 있던 메세지 제거.
		{
			TranslateMessage(&msg);
			DispatchMessage(&msg);
		}
		else // 메세지를 받지 않을 때는 게임루프를 수행.
		{
			timeMng->Tick(timeMng, 60.f);
			g_gameLoop->Update();
			g_gameLoop->Render();
		}
	}

	g_gameLoop->Release();
	DestroyWindow(g_hWnd); // 윈도우 파괴
	UnregisterClass(CURRENT_WND_CLASS, hInstance); // 윈도우 클래스 등록 해제

	return (int)msg.wParam;
}

//  목적: 창 클래스를 등록합니다.
ATOM MyRegisterClass(HINSTANCE hInstance)
{
	WNDCLASSEXW wcex;

	wcex.cbSize = sizeof(WNDCLASSEX);

	wcex.style = CS_HREDRAW | CS_VREDRAW;
	wcex.lpfnWndProc = WndProc;
	wcex.cbClsExtra = 0;
	wcex.cbWndExtra = 0;
	wcex.hInstance = hInstance;
	wcex.hIcon = LoadIcon(hInstance, MAKEINTRESOURCE(IDC_ASSEMBLEGAMEPROJECT));
	wcex.hCursor = LoadCursor(NULL, IDC_ARROW);
	wcex.hbrBackground = (HBRUSH)(COLOR_WINDOW + 1);
	wcex.lpszMenuName = NULL; // 메뉴 없애기
	wcex.lpszClassName = CURRENT_WND_CLASS;
	wcex.hIconSm = LoadIcon(wcex.hInstance, MAKEINTRESOURCE(IDI_SMALL));

	return RegisterClassExW(&wcex);
}

//   목적: 인스턴스 핸들을 저장하고 주 창을 만듭니다.
//  
//   설명: 이 함수를 통해 인스턴스 핸들을 전역 변수에 저장하고
//         주 프로그램 창을 만든 다음 표시합니다.
BOOL InitInstance(HINSTANCE hInstance, int nCmdShow)
{
	g_hInst = hInstance; // 인스턴스 핸들을 전역 변수에 저장합니다.

	SetRect(&winRC, 0, 0, WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT); // rc를 설정.
	AdjustWindowRect(&winRC, WS_CAPTION | WS_SYSMENU/*WS_OVERLAPPEDWINDOW*/, FALSE); // 원하는 작업영역을 rc에 설정.

	g_hWnd = CreateWindowW(CURRENT_WND_CLASS, CURRENT_TITLE, WS_OVERLAPPEDWINDOW,
		0/*CW_USEDEFAULT*/, 0, winRC.right - winRC.left/*WINDOW_SIZE_WIDTH*/, winRC.bottom - winRC.top/*WINDOW_SIZE_HEIGHT*/, NULL, NULL, hInstance, NULL);

	//SetWindowPos(g_hWnd, NULL, 0, 0, WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT, SWP_NOMOVE | SWP_NOZORDER);
	//MoveWindow(g_hWnd, 0, 0, WINDOW_SIZE_WIDTH, WINDOW_SIZE_HEIGHT, TRUE);

	if (!g_hWnd)
	{
		return FALSE;
	}

	ShowWindow(g_hWnd, nCmdShow);
	//UpdateWindow(g_hWnd);

	return TRUE;
}

LRESULT CALLBACK WndProc(HWND hWnd, UINT message, WPARAM wParam, LPARAM lParam)
{
	switch (message)
	{
	case WM_COMMAND:
		break;
	default:
		return MainGameProc(hWnd, message, wParam, lParam);
	}

	return 0;
}
