#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

bool isPalindromo(char * str){
  int tam = strlen(str) - 1;
  for(int i = 0, j = tam; i <= tam/2; i++, j--){
    if(*(str+i) != *(str + j))
      return false;
  }
  return true;
}

int main(void) {
  char * palavra = (char*)malloc(51*sizeof(char));
  for(;;){
    if(strcmp(palavra, "FIM") == 0){
      break;
    }
    scanf(" %[^\n]", palavra);
    
    // palavra = (char*)realloc(palavra, (tam+1)*sizeof(char));
    if(isPalindromo(palavra)){
      printf("SIM\n");
    }else{
      printf("NAO\n");
    }
  }
  free(palavra);
  return 0;
}