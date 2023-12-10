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

void swap(Jogador *x, Jogador *y ){
    Jogador temp = *x;
    *x = *y;
    *y = temp;
}

Jogador findMax(Jogador arr[], int n) {
    Jogador max = arr[0];
    for (int i = 1; i < n; i++) {
        if (arr[i].id > max.id) {
            max = arr[i];
        }
    }
    return max;
}

void radixSort(Jogador arr[], int n) {
    Jogador max = findMax(arr, n);

    for (int exp = 1; max.id / exp > 0; exp *= 10) {
        Jogador output[n];
        int count[10] = {0};

        for (int i = 0; i < n; i++) {
            count[(arr[i].id / exp) % 10]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i].id / exp) % 10] - 1] = arr[i];
            count[(arr[i].id / exp) % 10]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}


int main()
{

    //FILE *arq = fopen("C:\\Users\\Pichau\\Desktop\\TP02\\Q14\\players.csv", "r");
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

    Jogador jg_entrada[qnt_jg];
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
    radixSort(jg_entrada, index_jg_entrada);
    for(int i = 0; i < index_jg_entrada; i++){
        imprimir(jg_entrada[i]);
    }

    
    return 0;
}