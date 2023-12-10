#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>


void combinador(char* str1, char* str2){
	int t1  = strlen(str1);
    int t2  = strlen(str2);
  int tam = t1 + t2;
  
  for(int i = 0; i < tam;i++){
    if(i < t1){
      printf("%c", str1[i]);
    }
    if(i < t2){
      printf("%c", str2[i]);
    }
  }
  printf("\n");
}

int main(void) {
  char str1[51];
  char str2[51];
  int n;
  scanf("%d", &n);
  for(int i = 0; i < n; i++){
    scanf("%s", str1);
    scanf("%s", str2);
    combinador(str1,str2);
  }
  return 0;
}

