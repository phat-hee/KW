#include <stdio.h>
#include <stdlib.h>

#define LINE 30

int main()
{
	FILE *fp1, *fp2;
	char line[LINE];

	fp1 = fopen("basic.txt", "r");
	if (fp1 == NULL)
	{
		printf("파일을 열 수 없습니다.\n");
		exit(1);
	}

	fp2 = fopen("linetarget.txt", "w");

	while (fgets(line, LINE, fp1) != NULL)
		fputs(line, fp2);

	fclose(fp1);
	fclose(fp2);

	return 0;
}