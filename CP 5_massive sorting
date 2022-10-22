#include <stdio.h>
#include <math.h>
#define MAX 100

int main() {
  int a[MAX][MAX], b[MAX], i, j, n, m, sum=0, el=0, el_b, el_bd, buf, min, g, s, us;
  printf("Enter number of strokes: ");
  scanf("%d", &i);
  printf("Enter number of collums: ");
  scanf("%d", &j);
  for(n = 0; n <= i-1; n++){
    for(m = 0; m <= j-1; m++){
    printf("Element of array a[%d][%d]: ", n, m);
    scanf("%d", &a[n][m]);
    }
  }
  printf("\n\t<<<ARRAY>>>\n");
  for(n = 0; n <= i-1; n++){
    printf("\n");
    sum = 0;
    for(m = 0; m <= j-1; m++){
      printf("\t%d", a[n][m]);
      sum = sum + a[n][m];
    }
    b[n] = sum;
    el++;
  }
  for(el_b = 0; el_b < el-1; el_b++) { 
    min=el_b;
    for(el_bd = el_b+1; el_bd < el; el_bd++){ 
      if(b[el_bd] > b[min]){ 
        min = el_bd;}
        buf=b[el_b];
        b[el_b]=b[min];
        b[min]=buf;
    }
  }
  printf("\n\n\t<<<NEW ARRAY>>>\n");
  buf = 0;
  for(s = 0; s <= el-1; s++){
    for(n = 0; n <= i-1; n++){
      for(m = 0; m <= j-1; m++){
        buf = buf + a[n][m];}
      if(buf == b[s]){
        printf("\n%d:",b[s]);
        us=1;
        for(g=0; g <= j-1; g++){
          printf("\t%d", a[n][g]);
        }
      }
      buf=0;
    }     
  }  
  return 0;
}
