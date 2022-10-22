#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

void find(char*c)
{
	char delim[]=" ", *w, words[10][100], temp, ts[10][100];
	int i=0, j, k, l, t=0;
	w=strtok(c,delim);//perenosim dannye s delim in c
		do{
		words[i++][0]='\0';
		strcpy(words[i-1],w);
		w=strtok(NULL,delim);
	}while(w!=NULL);
	for(j=0;j<i;j++)
	{
		ts[j][0]='\0';
		strcpy(ts[j],words[j]);
	}//copiruem dannye v massiv
	for(j=0;j<i;j++)
		for(k=0;k<strlen(words[j])-1;k++)
			for(l=k;l<strlen(words[j]);l++)
			if(words[j][k]>words[j][l])
			{
				temp=words[j][k];
				words[j][k]=words[j][l];
				words[j][l]=temp;
			}//obmin bublashkou
	for(j=0;j<i-1;j++)
		for(l=j+1;l<i;l++)
		if (strcmp(words[j],words[l])==0)
		{
			printf("Same word: %s", ts[j]);
			getchar();
			exit(0);
		}
		printf("No equal words\n");
}

int main()
{
	char c[10000], str[80], *p=str;
	int k=0; 
	printf("Enter text:\n");
	gets(c);
	find(c);
	return 0;
}
