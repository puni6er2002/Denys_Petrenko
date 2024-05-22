#include <iostream>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <fstream>
using namespace std;

struct Alphabet // оглашаем структуру
{
  char letters[40];
  char symbols[40];
  int lc;
  int sc;
}*p;

void base(int N) // создаём структуру
{
  p = new Alphabet[N];
  for (int i = 0; i < N; i++)
  {
    cout << "Enter letters of the alphabet\n"; // буквы
    cin >> p[i].letters;
    cout << "Enter special symbols of the alphabet\n"; //символы
    cin >> p[i].symbols;
    p[i].lc = strlen(p[i].letters); // количество букв и количество символов
    p[i].sc = strlen(p[i].symbols);
  }
}

void print(int N) //вывод
{
  for (int i = 0; i < N; i++)
  {
    cout <<i + 1 << ". Alphabet contains " << p[i].lc << " letters: " << p[i].letters << " and  " << p[i].sc << "  special symbols: " << p[i].symbols << "\n";

  }
}

int comb(int k, int N) // функция подсчёта элементов, что имеют определённое количество букв и символов
{
  int m=0;
  for (int i = 0; i < N; i++)
  {
    if ((p[i].lc + p[i].sc) == k)
    {
      m++;
      cout << "i + 1  " << ".Alphabet contains " << p[i].lc << " letters:  " << p[i].letters << " and   " << p[i].sc << " special symbols :   " << p[i].symbols << "\n";
    }
  }
  return m;
}


void how(int N) // функция для подсчёта элементов. Здесь ты только вводишь данные
{
  int k,m;
  cout << "Enter how much symbols you require\n";
  cin >> k;
  m = comb(k, N);
  cout << "Total " << m << "\n";
}

void swap(int a, int b) //Функция меняет местами 2 элемента
{
  char temp1[40];
  char temp2[40];
  int t1, t2;
  strcpy(temp1, p[a].letters);
  strcpy(temp2, p[a].symbols);
  t1 = p[a].lc;
  t2 = p[a].sc;
  strcpy(p[a].letters, p[b].letters);
  strcpy(p[a].symbols, p[b].symbols);
  p[a].lc = p[b].lc;
  p[a].sc = p[b].sc;
  strcpy(p[b].letters, temp1);
  strcpy(p[b].symbols, temp2);
  p[b].lc = t1;
  p[b].sc = t2;
}

void sort(int N) //функция сортирует
{
  for (int j = 0; j<N; j++){
  for (int i = 1; i < N; i++)
  {
    if (p[i].lc < p[i-1].lc)
    {
      swap(i - 1, i); // переставляет элементы с помощью функции перестановки
    }
  }
  }
  cout << "Base is sorted\n";
}

void addel(int N, int k) //функция которая добавляет элемент после к-го
{
  p=(Alphabet*)realloc(p,sizeof(Alphabet)*(N+1));
  for (int i = N; i > k-1; i--) //Сдвиг всех элементов вправо
  {
    strcpy(p[i].letters, p[i-1].letters);
    strcpy(p[i].symbols, p[i-1].symbols);
    p[i].lc = p[i-1].lc;
    p[i].sc = p[i-1].sc;
  }
  cout << "Enter letters of the alphabet\n";
  cin >> p[k].letters;
  cout << "Enter special sybols of the alphabet\n";
  cin >> p[k].symbols;
  p[k].lc = strlen(p[k].letters);
  p[k].sc = strlen(p[k].symbols);
}

void changel(int N) //функция для для ввода элементов для замены
{
  int k, j;
  system("cls");
  cout << "Enter elements to swap\n";
  cin >> k;
  cin >> j;
  swap(k - 1, j - 1);
}

void adel(int N) // функция для ввода элемента, после которого добавляем элемент
{
  int k;
  cout << "Enter number of element after which you want to add\n";
  cin >> k;
  addel(N, k);
}

void write(int n) // создание файла
{
    FILE *fp;
    if(!(fp=fopen("file.txt", "wb")))
 {
     printf("Cannot open file\n");
        return;
    }

    for(int i = 0;i<n;i++)
 {
     fwrite(&p[i], sizeof(Alphabet), 1, fp);
    }
 fclose(fp);
}
int main()
{
  int N,f;
  cout<<"Enter dimension of database.\n";
  cin>>N;

  base(N);
  do
  {
    print(N);
    cout << "   Menu   \n";
    cout << "1.Count alphabets with needed characters\n";
    cout << "2.Sort by letters number\n";
    cout << "3.Add new element after certain\n";
    cout << "4.Change place of two elements\n";
    cout << "5.Save data to the file\n";
    cout << "6.Exit\n";
    cin >> f;
    switch (f)
    {
    case 1: {how(N); break;}
    case 2: { sort(N); break;}
    case 3: { adel(N); N++; break;}
    case 4: { changel(N); break;}
    case 5: { write(N); break;}
    case 6: break;
    }
  } while (f != 6);
}
