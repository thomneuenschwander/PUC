#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

bool fim (char str []){
  int tam = strlen(str);
  char fim[4] = {'F', 'I', 'M'};
  if(tam != 3){
    return false;
  }else{
    for(int i = 0; i < tam; i++){
      if(str[i] != fim[i]){
        return false;
      }
    } 
  }
  return true;
}
bool isPalindromo(int esq, int dir, char str[]){
  if(esq >= dir/2){
    return true;
  }else if(str[esq] != str[dir]){
    return false;
  }else{
    return isPalindromo(1+esq, -1 + dir, str);
  }
}
int main(void) {
  char str[51];
  while(!fim(str)){
    scanf(" %[^\n]", str);
    if(!fim(str)){
      if(isPalindromo(0, strlen(str)-1,str)){
        printf("SIM\n");
      }else{
        printf("NAO\n");
      }
    }
  }
  return 0;
}