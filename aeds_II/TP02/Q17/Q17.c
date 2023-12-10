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

void swap(Jogador *x, Jogador *y ){
    Jogador temp = *x;
    *x = *y;
    *y = temp;
}


void insertionSort(Jogador *arr, int tam){

    for(int i = 1; i < tam; i++){
        Jogador temp = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j].anoNascimento > temp.anoNascimento)
        {
            arr[j+1] = arr[j];
            j--;
        }
        arr[j+1] = temp;
    }
}

void PARCIALinsertionSort(Jogador *arr, int tam, int k){
    for(int i = 1; i < tam; i++){
        Jogador temp = arr[i];
        int j = i - 1;

        while (j >= 0 && arr[j].anoNascimento > temp.anoNascimento)
        {
            arr[j+1] = arr[j];
            j--;
        }
        arr[j+1] = temp;

        if(i >= k){
            j = k - 1;
            while(j > 0 && arr[j].anoNascimento == arr[k-1].anoNascimento){
                j--;
            }
            for(int m = k; m < i; m++){
                arr[m] = arr[m + 1];
            }
        }


    }
}
void insertionSortParcial (Jogador* jogador, int tam, int k)
    {
        for (int i = 1; i < tam; i++)
        {
            Jogador temp = jogador[i];
            int j = i < k ? i - 1 : k - 1; 
            while (j >= 0 && (jogador[j].anoNascimento > temp.anoNascimento|| (jogador[j].anoNascimento == temp.anoNascimento && strcmp(jogador[j].nome, temp.nome) > 0)))
            {
                jogador[j+1] = jogador[j]; 
                j--;
            }
            jogador[j+1] = temp; 
        }
    }
int comparar(const void *a, const void *b)
{
    Jogador *jogadorA = (Jogador *)a;
    Jogador *jogadorB = (Jogador *)b;

    int resultadoAltura = (jogadorA->altura) - (jogadorB->altura);

    if (resultadoAltura == 0)
    {
        return strcmp(jogadorA->nome, jogadorB->nome);
    }
    else
    {
        return resultadoAltura;
    }
}
void maxHeapify(Jogador *arr, int n, int i) {
    int maior = i;
    int esq = 2 * i + 1;
    int dir = 2 * i + 2;

    if (esq < n && comparar(&arr[esq], &arr[maior]) > 0) {
        maior = esq;
    }

    if (dir < n && comparar(&arr[dir], &arr[maior]) > 0 > 0) {
        maior = dir;
    }

    if (maior != i) {
        Jogador temp = arr[i];
        arr[i] = arr[maior];
        arr[maior] = temp;
        maxHeapify(arr, n, maior);
    }
}

void buildMaxHeap(Jogador *arr, int n) {
    for (int i = n / 2 - 1; i >= 0; i--) {
        maxHeapify(arr, n, i);
    }
}

void heapsort(Jogador *arr, int n) {
    buildMaxHeap(arr, n);

    for (int i = n - 1; i > 0; i--) {
        Jogador temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        maxHeapify(arr, i, 0);
    }
}

int main()
{

    //FILE *arq = fopen("C:\\Users\\Pichau\\Desktop\\TP02\\Q17\\players.csv", "r");
    FILE * arq = fopen("/tmp/players.csv", "r");

    if(arq == NULL){
        printf("falha no arquivo\n");
        return 1;
    }

    char linha[1001];
    int qnt_jg = 3922;
    Jogador jg_vet[qnt_jg];
    int jg_index = 0;

    fgets(linha, sizeof(linha), arq);
    while (fgets(linha, sizeof(linha), arq) != NULL)
    {
        int tam = strlen(linha);
        jg_vet[jg_index] = ler(linha, tam);
        jg_index++;
    }
    fclose(arq);
    char entrada[35];
    strcpy(entrada, "entrada");

    Jogador *jg_entrada = malloc(qnt_jg*sizeof(Jogador));
    int index_jg_entrada = 0;

    while (strcmp(entrada, "FIM") != 0)
    {
        scanf(" %[^\n]", entrada);
        if (strcmp(entrada, "FIM") != 0)
        {
            int id_input = atoi(entrada);
            jg_entrada[index_jg_entrada] = buscarJogadorPorId(id_input, jg_vet, qnt_jg);
            index_jg_entrada++;
        }
    }
    jg_entrada = realloc(jg_entrada, index_jg_entrada*sizeof(Jogador));
    // insertionSort(jg_entrada, index_jg_entrada);
    int k = 10;
    // PARCIALinsertionSort(jg_entrada, index_jg_entrada, k);
    // insertionSortParcial(jg_entrada, index_jg_entrada, k);
    heapsort(jg_entrada, index_jg_entrada);

    for(int i = 0; i < k; i++){
        imprimir(jg_entrada[i]);
    }

    
    return 0;
}

