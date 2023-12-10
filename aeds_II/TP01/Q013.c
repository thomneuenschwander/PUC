#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>


void troca(char l1, char l2, char * str){
  for(int i = 0; i < strlen(str); i++){
    if(str[i] == l1){
      strncpy(&str[i], &l2, 1);
    }
  }
}
int main(void) {
  char str[51];
  srand(4);
  while(strcmp(str, "FIM") != 0){
    scanf(" %[^\n]", str);
    char text[51];
    strcpy(text, str);
    if(strcmp(str, "FIM") != 0){  
    char l1 = (char)((rand()%26)+'a');
    char l2 = (char)((rand()%26)+'a');
    // printf("%c", l1);
    // printf("%c", l2);
    troca(l1,l2,text);
    printf("%s\n", text);
    }
  }
  return 0;
}