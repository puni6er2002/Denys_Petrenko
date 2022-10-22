#include <stdio.h>
#include <math.h>
typedef float (*function)(float);
float f(float x)
{
    return(1/(pow(x+1, 1/3.0)));
}
float integralpram(float a,float b,int n, function f)
{
    float h,S,x, i;
    h=(b-a)*1.0/n;
    S=0;
    for(i=0;i<n-1;i++)
    {
        x=a+i*h;
        S=S+f(x);
    }
    S=h*S;
    return S;
}//Metod priamokutnyka
int main()
{
   float  y;
   y=integralpram(0,1.2,1000, f);//Znahodymo znachennia integrala v mezhah vid 0 do 1.2
   printf("%f", y);
}
