#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

int main(void) {

  int n;
  scanf("%d", &n);
  //Abre o arquivo para a escrita
  FILE * arq = fopen("arquivo9.txt", "w");
  for(int i = 0; i < n; i++){
    float num;
    scanf("%f", &num);
    fprintf(arq, "%.1f\n", num);
  }
  fclose(arq);
  //abre o arquivo para a leitura
  arq = fopen("arquivo9.txt", "r");
  //posiciona o ponteiro no final do arquivo
  fseek(arq, 0, SEEK_END);
  //obtem o tamanho do arquivo
  int tam = ftell(arq);
  //reserva memoria para armazenar o conteudo
  char* content = (char*)malloc(tam + 1);

  fseek(arq,0,SEEK_SET);

  for(int i = tam - 1; i>=0; i--){
    fseek(arq, i, SEEK_SET);
    content[tam - i - 1] = fgetc(arq);
  }
  content[tam] = '\0';

  fclose(arq);
  printf("%s",content);
  free(content);
  return 0;
}