#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

void selectionSort(int*arr, int tam){
    for(int i = 0; i < tam-1;i++){
        for(int j = (i+1); j < tam; j++){
            if(arr[i] > arr[j]){
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
    }
}
bool verificar(int* arr, int tam){
    for(int i = 0; i < tam-1; i++){
        if(arr[i+1] > arr[i]+1){
            return false;
        }
    }
    return true;
}

int* getSorteados(int* matriz, int*sorteados, int n, int m, int u, int* winTam){
    int* ganhadores = malloc(n*sizeof(int));
    int idx = 0;
    for(int linhas = 0; linhas < n; linhas++)
    {
        int posicoes[m];
        int index = 0;

        for(int col = 0; col < m; col++){
            for(int k = 0; k < u; k++){
                if(*(matriz+linhas*m+col) == sorteados[k]) {
                    posicoes[index++] = k;
                }
            }
        }
        selectionSort(posicoes, index);
        
        if(verificar(posicoes, index)){
            ganhadores[idx++] = linhas+1;
        }
    }
    *winTam = idx;
    return ganhadores;
}

int main(){
    int n, m, u;
    scanf("%d %d %d", &n, &m, &u);
    int* matriz = malloc(n*m*sizeof(int));
    for(int i = 0; i < n; i++){
        for(int j = 0; j < m; j++){
            scanf("%d", (matriz+i*m+j));
        }
    }
    int * sorteado = malloc(u*sizeof(int));
    for(int i = 0; i < u; i++){
        scanf("%d", &sorteado[i]);
    }
    int ganhadoresTamanho = 0;
    int* ganhadores =  getSorteados(matriz, sorteado, n, m, u, &ganhadoresTamanho);
    for(int i = 0; i < ganhadoresTamanho; i++){
        printf("%d\n", ganhadores[i]);
    }

    return 0;
}