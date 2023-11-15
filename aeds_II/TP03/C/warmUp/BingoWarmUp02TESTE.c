#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

// void imprimir(int* matriz, int n, int m){
//     for(int i = 0; i < n; i++){
//         for(int j = 0; j < m; j++){
//             printf("%d ", *(matriz+i*m+j));
//         }
//     }
// }
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


void sorteado(int* matrix, int n,int m,  int *sorteados, int sortTam){
    int acertos[n]; // cada pos desse array tera a quantidade de acertos de cada linha

    int ganhadores[n];
    int index = 0;
    int ganharam = 0;
    for(int i = 0; i < n; i++){acertos[i] = 0;}

    for(int s = 0; s < sortTam && ganharam == 0; s++)
    {
        for(int lin = 0; lin < n && ganharam == 0; lin++)
        {
            for(int col = 0; col < m; col++)
            {
                if(*(matrix+lin*m+col) == sorteados[s]){
                    acertos[lin] = acertos[lin] + 1;
                }
            }
        }
        // verificar se algum elemento esta completo
        for(int l = 0; l < n; l++){
            if(acertos[l] == m){
                ganhadores[index++] = l+1;
                ganharam = 1;
            }
        }
    }
    selectionSort(ganhadores, index);
  // printa os q ganharam
    for(int i = 0; i < index; i++){
        printf("%d\n", ganhadores[i]);
    }
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
    int numeros[u];
    for(int i = 0; i < u; i++){
        scanf("%d", &numeros[i]);
    }
    sorteado(matriz, n, m, numeros, u);

    return 0;
}