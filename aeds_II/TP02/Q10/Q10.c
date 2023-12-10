#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>
#include <math.h>

typedef struct Jogador
{
    int id;
    char nome[31];
    int altura;
    int peso;
    char universidade[31];
    int anoNascimento;
    char cidadeNascimento[31];
    char estadoNascimento[31];
} Jogador;

void imprimir(Jogador jogador)
{
    printf("[");
    printf("%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
    printf("]\n");
}

Jogador buscarJogadorPorId(int id, Jogador *jogadores, int qnt_jg)
{
    for (int i = 0; i < qnt_jg; i++)
    {
        if (id == jogadores[i].id)
        {
            return jogadores[i];
        }
    }
    Jogador jogadorNotFound;
    jogadorNotFound.id = -1;
    return jogadorNotFound;
}

Jogador ler(char *linha, int tam)
{
    Jogador jogador;
    int index[7];
    int virg = 0;
    for (int i = 0; i < tam; i++)
    {
        if (linha[i] == ',')
        {
            index[virg] = i;
            virg++;
        }
    }
    int i = 0;
    int j = 0;
    char id[5];
    while (i < index[0])
    {
        id[j] = linha[i];
        j++;
        i++;
    }
    id[j] = '\0';
    int id_integer = atoi(id);
    jogador.id = id_integer;
    i = index[0] + 1;
    j = 0;
    char nome[21];
    while (i < index[1])
    {
        nome[j] = linha[i];
        j++;
        i++;
    }
    nome[j] = '\0';
    strcpy(jogador.nome, nome);
    i = index[1] + 1;
    j = 0;
    char altura[4];
    while (i < index[2])
    {
        altura[j] = linha[i];
        j++;
        i++;
    }
    altura[j] = '\0';
    int altura_integer = atoi(altura);
    jogador.altura = altura_integer;
    i = index[2] + 1;
    j = 0;
    char peso[4];
    while (i < index[3])
    {
        peso[j] = linha[i];
        j++;
        i++;
    }
    peso[j] = '\0';
    int peso_integer = atoi(peso);
    jogador.peso = peso_integer;
    i = index[3] + 1;
    j = 0;
    char universidade[31];
    if ((index[4] - 1) - index[3] + 1 != 1)
    {
        while (i < index[4])
        {
            universidade[j] = linha[i];
            j++;
            i++;
        }
        universidade[j] = '\0';
    }
    else
    {
        strcpy(universidade, "nao informado");
    }
    strcpy(jogador.universidade, universidade);
    i = index[4] + 1;
    j = 0;
    char anoNascimento[5];
    while (i < index[5])
    {
        anoNascimento[j] = linha[i];
        j++;
        i++;
    }
    anoNascimento[j] = '\0';
    int anoNascimento_integer = atoi(anoNascimento);
    jogador.anoNascimento = anoNascimento_integer;
    i = index[5] + 1;
    j = 0;
    char cidadeNascimento[31];

    if (index[6] - index[5] + 1 != 2)
    {
        while (i < index[6])
        {
            cidadeNascimento[j] = linha[i];
            j++;
            i++;
        }
        cidadeNascimento[j] = '\0';
    }
    else
    {
        strcpy(cidadeNascimento, "nao informado");
    }
    strcpy(jogador.cidadeNascimento, cidadeNascimento);
    i = index[6] + 1;
    j = 0;
    char estadoNascimento[31];
    if ((tam - 3) - index[6] + 1 != 0)
    {
        while (i < tam - 1)
        {
            estadoNascimento[j] = linha[i];
            j++;
            i++;
        }
        estadoNascimento[j] = '\0';
    }
    else
    {
        strcpy(estadoNascimento, "nao informado");
    }
    strcpy(jogador.estadoNascimento, estadoNascimento);

    return jogador;
}

void selectionSort(Jogador *arr, int tam)
{
    for (int i = 0; i < tam - 1; i++)
    {
        for (int j = (i + 1); j < tam; j++)
        {
            int comparacao = strcmp(arr[i].nome, arr[j].nome);
            if (comparacao > 0)
            {
                char tmp[31];
                strcpy(tmp, arr[i].nome);
                strcpy(arr[i].nome, arr[j].nome);
                strcpy(arr[j].nome, tmp);
            }
        }
    }
}

// void quickSort(int esq, int dir, Jogador * arr){
//     int i = esq, j = dir;
//     char pivo[41];
//     strcpy(pivo, arr[(esq+dir)/2].estadoNascimento);

//     while(i <= j){
//         while(strcmp(arr[i].estadoNascimento, pivo) < 0){ i++; }
//         while(strcmp(arr[j].estadoNascimento, pivo) > 0){ j++; }

//         if(i <= j){
//             swap(&arr[i], &arr[j]);
//             i++;
//             j--;
//         }
//     }
//     if(esq < j){
//         quickSort(esq, j, arr);
//     }
//     if(i < dir){
//         quickSort(i, dir, arr);
//     }
// }

int pesquisaBinaria(Jogador *arr, char *nome, int tam)
{
    int achou = 0;
    int esq = 0;
    int dir = tam - 1;
    int meio;
    while (esq <= dir)
    {
        meio = (dir + esq) / 2;
        int comparacao = strcmp(arr[meio].nome, nome);

        if (comparacao == 0)
        {
            achou = 1;
            return achou;
        }
        else if (comparacao < 0)
        {
            esq = meio + 1;
        }
        else
        {
            dir = meio - 1;
        }
    }
    return achou;
}

// int divisao(Jogador*arr, int esq, int dir){
//     char piv[31];
//     strcpy(piv, arr[dir].estadoNascimento);
//     int i = (esq - 1);

//     for(int j = esq; j <= dir - 1; j++){
//         int comparacao = strcmp(arr[j].estadoNascimento, piv);
//         if(comparacao < 0){
//             i++;
//             swap(&arr[i], &arr[j]);
//         }
//     }
//     swap(&arr[i + 1], &arr[dir]);
//     return (i + 1);
// }

// void quickSort(Jogador*arr, int esq, int dir){
//     if(esq < dir) {
//         int pi = divisao(arr, esq, dir);

//         quickSort(arr, esq, pi - 1);
//         quickSort(arr, pi + 1, dir);
//     }
// }
void swap(Jogador *x, Jogador *y ){
    Jogador temp = *x;
    *x = *y;
    *y = temp;
}
void quicksort(Jogador *arr, int n) {
    quicksortRec(arr, 0, n-1);
}

void quicksortRec(Jogador* arr, int esq, int dir) {
    int i = esq, j = dir;
    Jogador pivo = arr[(esq + dir) / 2];
    while (i <= j) {
        while (strcmp(arr[i].estadoNascimento, pivo.estadoNascimento) < 0) {
            i++;
        }
        while (strcmp(arr[j].estadoNascimento, pivo.estadoNascimento) > 0) {
            j--;
        }
        if (i <= j) {

            if (strcmp(arr[i].estadoNascimento, arr[j].estadoNascimento) == 0) {
                if (strcmp(arr[i].nome, arr[j].nome) > 0) {
                    swap(&arr[j], &arr[i]);
                }
            } else {
                swap(arr + j, arr+i);
            }
            i++;
            j--;
        }
    }
    if (esq < j) {
        quicksortRec(arr, esq, j);
    }
    if (i < dir) {
        quicksortRec(arr, i, dir);
    }
}

int main()
{
    clock_t start_time, end_time;
    double time;
    start_time = clock();

    int comp = 0;

    //FILE *arq = fopen("C:\\Users\\Pichau\\Desktop\\TP02\\Q10\\players.csv", "r");
    FILE * arq = fopen("/tmp/players.csv", "r");

    char linha[1001];
    int qnt_jg = 3923;
    Jogador jg_vet[qnt_jg];
    int jg_index = 0;

    fgets(linha, sizeof(linha), arq);
    while (fgets(linha, sizeof(linha), arq) != NULL)
    {
        comp++;
        int tam = strlen(linha);
        jg_vet[jg_index] = ler(linha, tam);
        jg_index++;
    }
    fclose(arq);
    char entrada[45];
    strcpy(entrada, "entrada");

    Jogador*jg_entrada = malloc(qnt_jg*sizeof(Jogador));
    int index_jg_entrada = 0;

    while (strcmp(entrada, "FIM") != 0)
    {
        comp++;
        scanf(" %[^\n]", entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            comp++;
            int id_input = atoi(entrada);
            jg_entrada[index_jg_entrada] = buscarJogadorPorId(id_input, jg_vet, qnt_jg);
            index_jg_entrada++;
        }
    }
    jg_entrada = realloc(jg_entrada,index_jg_entrada*sizeof(Jogador));

    // selectionSort(jg_entrada, index_jg_entrada);
    quickSort(jg_entrada, index_jg_entrada);
    // ---------------- IMPRIMIR ----------------------
    for(int i = 0; i < index_jg_entrada; i++){
        imprimir(jg_entrada[i]);
    }

    
    comp += (int)log2(index_jg_entrada) + 1;
    end_time = clock();
    time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;

    arq = fopen("matricula_quicksort.txt", "w");
    fprintf(arq, "802717\t%f\t%d\n", time, comp);
    free(arq);
    fclose(arq);
    return 0;
}