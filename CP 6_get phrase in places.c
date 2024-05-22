#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *del (char *s, char *s1, char *s2)
{
    char *p, *t;
    for (p=strstr(s, s1); p; p=strstr(s, s1))
	//strstr - vozrashchaet ukazatel na mesto s, gde v pervyy raz poyavliaetsia s1
    {
        t=strdup (p+strlen(s1));//dubliruem stroku s videleniem pamyati pod novuu stroku
		// srtlen - vyznachaem dlinu masiva s1   
        memset(p, 0, sizeof(p));//zapolniaem masiv ukazanymi simvolami s kodom 0
        strcpy(p, s2);//kopiruem informaciu s masiva s2 v p
        strcat(p, t);//kopiruem stroku s masiva t i obyedinyaem s masivom p 
        free(t);//udaliem ukazatel
    }
    return s;
}


int main()
{
    char str[100];
    char s1[20]= "$", s2[20];
    char buffer[200];
    puts("Enter text\n");
    gets(str);
    puts("Enter word for including except $\n");
    gets(s2);
    printf("%s \n", del(str, s1, s2));
    return 0;
}
