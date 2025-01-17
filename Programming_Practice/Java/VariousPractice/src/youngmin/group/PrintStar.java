package youngmin.group;
import java.util.Scanner;

public class PrintStar {
	
	private int blankNum;
	private boolean isSide;
	
	public PrintStar() {}
	public PrintStar(boolean _isSide)
	{
		isSide = _isSide;
	}
	
	public void setBlankNum(int _blankNum)
	{
		blankNum = _blankNum;
	}
	
	public void checkBlank()
	{
		if (isSide) System.out.print("*");
		
		if (blankNum > 0)
		{
			isSide = false;
			blankNum--;
			System.out.print(" ");
		}
		else
			isSide = true;
	}
	
	public void printRhombus(boolean _isSide)
	{
		isSide = _isSide;
		
		int inputSideNum;
		
		Scanner scan = new Scanner(System.in);
		
		System.out.print("마름모 변 길이 입력 >> ");
		inputSideNum = scan.nextInt();
		
		for (int i = 1; i < inputSideNum; i++)
		{
			for (int j = i; j < inputSideNum; j++)
			{
				System.out.print(" ");
			}
			
			setBlankNum((i - 1) * 2 - 1);
			for (int j = 1; j < i * 2; j++)
			{
				checkBlank();
			}
				
			System.out.println();
		}
		
		for (int i = inputSideNum; i > 0; i--)
		{
			for (int j = i; j < inputSideNum; j++)
			{
				System.out.print(" ");
			}
			
			setBlankNum((i - 1) * 2 - 1);
			for (int j = 1; j < i * 2; j++)
			{
				checkBlank();
			}
			
			System.out.println();
		}
		
		scan.close();
	}
	
	public void printPyramid()
	{
		Scanner scan = new Scanner(System.in);
		
		System.out.print("라인 수 입력 >> ");
		int inputLineNum = scan.nextInt();
		
		for (int i = 1; i <= inputLineNum; i++)
		{
			for (int j = i; j <= inputLineNum; j++)
			{
				System.out.print(" ");
			}
			for (int j = 1; j < i * 2; j++)
			{
				System.out.print("*");
			}
			System.out.println();
		}
		
		scan.close();
	}
	
	public static void main(String[] args) {
		
		PrintStar printStar = new PrintStar();
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("1. 빈 마름모");
		System.out.println("2. 피라미드");
		System.out.println("3. ???");
		System.out.print("번호 입력 >> ");
		int inputKindNum = scan.nextInt();
		switch (inputKindNum)
		{
		case 1:
			printStar.printRhombus(true);
			break;
		case 2:
			printStar.printPyramid();
			break;
		case 3:
			break;
		default:
			break;
		}
		
		scan.close();
	}
}
