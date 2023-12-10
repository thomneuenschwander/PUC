#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>
bool isPalindromo(char * str){
  char * strSemEsp = (char*)malloc(30*sizeof(char));
  int count = 1;
  for(int i = 0; i < (strlen(str) - 1); i++){
    if(*(str + i) != ' '){
    *(strSemEsp + i) = *(str + i);
      count++;
    }
  }
  strSemEsp = (char*)realloc(str, count*sizeof(char));
  int j = strlen(strSemEsp) - 1;
  for(int i = 0; i < j; i++){
    if(*(strSemEsp + i) != *(strSemEsp + j)){
      return false;
    }
    j--;
  }
  return true;
}

int main(void) {
  char * str = (char*)malloc(30*sizeof(char));
  for(;;){
  scanf("%s", str);
  str = (char*)realloc(str, (strlen(str)+1)*sizeof(char));
    if(strcmp(str,"FIM") == 0){
      break;
    }
  if(isPalindromo(str)){
    printf("SIM\n");
  }else{
    printf("NAO\n");
  }
  }
free(str);
  return 0;
}