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
    int i = 0; // index da linha
    int j = 0; // index do vetor

    // armazenar o id
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

    // armazenar nome
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

    // armazenar a altura
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

    // armazenar peso
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

    // armazenar universidade
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

    // armazenar ano nascimento
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
//-------------------------------- LISTA FLEXIVEL DUPLA ----------------------------------------------
typedef struct CelulaDupla
{
    struct CelulaDupla* prox, *ant;
    struct Jogador elemento;
} CelulaDupla;

CelulaDupla* criarCelulaDupla(){
    CelulaDupla* novaCel = malloc(sizeof(CelulaDupla));
    novaCel->ant = NULL;
    novaCel->prox = NULL;
    return novaCel;
}
CelulaDupla* criarCelulaDuplaComElemento(Jogador x){
    CelulaDupla* novaCel = malloc(sizeof(CelulaDupla));
    novaCel->ant = NULL;
    novaCel->prox = NULL;
    novaCel->elemento = x;
    return novaCel;
}

typedef struct ListaFlexDupla
{
    struct CelulaDupla* primeiro, *ultimo;
    int tamanho;
} ListaFlexDupla;

ListaFlexDupla* criarListaFlexDupla(){
    ListaFlexDupla* novaLista = malloc(sizeof(ListaFlexDupla));
    novaLista->primeiro = criarCelulaDupla();
    novaLista->ultimo = novaLista->primeiro;
    novaLista->tamanho = 0;
    return novaLista;
}

//----------------------------FUNÇÕES LISTA FLEXIVEL DUPLA ----------------------------------------------------

    // ------------- MOSTRAR ------------------
void mostrarCrescente(ListaFlexDupla* lista){
    CelulaDupla* i = lista->primeiro->prox;
    for(; i != NULL; i = i->prox){
        imprimir(i->elemento);
    }
}
void mostrarDecrescente(ListaFlexDupla* lista){
    CelulaDupla* i = lista->ultimo;
    for(; i != lista->primeiro; i = i->ant){
        imprimir(i->elemento);
    }
}

    // ------------- INSERIR ------------------
void inserirFim(ListaFlexDupla* lista, Jogador x){
    lista->ultimo->prox = criarCelulaDuplaComElemento(x);
    lista->ultimo->prox->ant = lista->ultimo;
    lista->ultimo = lista->ultimo->prox;
    lista->tamanho++;
}
void inserirInicio(ListaFlexDupla* lista, Jogador x){
    CelulaDupla* tmp = criarCelulaDuplaComElemento(x);
    if(lista->primeiro == lista->ultimo){
        lista->ultimo->prox = tmp;
        lista->ultimo = lista->ultimo->prox;
    }else{
        tmp->ant = lista->primeiro;
        tmp->prox = lista->primeiro->prox;
        tmp->prox->ant = tmp;
        tmp->ant->prox = tmp;
    }
    lista->tamanho++;
}
void inserir(ListaFlexDupla* lista, Jogador x, int pos){
    int tam = lista->tamanho;
    if(pos == 0) {inserirInicio(lista, x);}
    else if(pos == tam) {inserirFim(lista, x);}
    else{
        CelulaDupla* i = lista->primeiro;
        for(int j = 0; j < pos; j++, i = i->prox);
        CelulaDupla* tmp = criarCelulaDuplaComElemento(x);
        tmp->ant = i;
        tmp->prox = i->prox;
        i->prox = tmp;
        tmp->prox->ant = tmp;
        lista->tamanho++;
    }
}
    // ------------- REMOVER ------------------
Jogador removerFim(ListaFlexDupla* lista){
    Jogador resp = lista->ultimo->elemento;
    CelulaDupla* tmp = lista->ultimo;
    lista->ultimo = lista->ultimo->ant;
    lista->ultimo->prox = NULL;
    tmp->ant = NULL;
    free(tmp);
    lista->tamanho--;
    return resp;
}

Jogador removerInicio(ListaFlexDupla* lista){
    Jogador resp = lista->primeiro->prox->elemento;
    CelulaDupla* tmp = lista->primeiro->prox;
    lista->primeiro->prox->prox->ant = lista->primeiro;
    lista->primeiro->prox = lista->primeiro->prox->prox;
    tmp->ant = NULL;
    tmp->prox = NULL;
    free(tmp);
    lista->tamanho--;
    return resp;
}

Jogador remover(ListaFlexDupla* lista, int pos){
    int tam = lista->tamanho;
    Jogador resp;
    if(pos == 0){resp = removerInicio(lista);}
    else if(pos == tam-1){resp = removerFim(lista);}
    else{
        CelulaDupla* i = lista->primeiro;
        for(int j = 0; j <= pos; j++, i = i->prox);
        resp = i->elemento;
        CelulaDupla* tmp = i;
        i->ant->prox = i->prox;
        i->prox->ant = i->ant;
        tmp->ant = NULL;
        tmp->prox = NULL;
        free(tmp);
        lista->tamanho--;
    }
    return resp;
}
  // ------------- ÚTEIS ------------------
Jogador procurarJogadorPorId(ListaFlexDupla* lista, int id)
{
    for(CelulaDupla* i = lista->primeiro->prox; i != NULL; i = i->prox)
    {
        if(i->elemento.id == id){
            return i->elemento;
        }
    }
    printf("Jogador nao encontrado!\n");
    Jogador error;
    error.id = -1;
    return error;
}
Jogador getMeio(ListaFlexDupla* lista){
    int pos = (lista->tamanho - 1)/2;
    CelulaDupla* i = lista->primeiro;
    for(int j = 0; j <= pos; j++, i = i->prox);
    return i->elemento;
}
void swapJogadores(CelulaDupla* x, CelulaDupla* y){
    Jogador tmp = x->elemento;
    x->elemento = y->elemento;
    y->elemento = tmp;
}
//.........................................................................................................
void swap(CelulaDupla *x, CelulaDupla *y) {
    Jogador temp = x->elemento;
    x->elemento = y->elemento;
    y->elemento = temp;
}
    // OBS: O quicksort dos slides da PUC e os ajustes passados pelo Gabriel para implementar na lista nao funcionaram.
    // Portanto, esse algoritimo de quicksort foi retirado de outra fonte.

  // ------------- QUICKSORT ------------------
CelulaDupla *slice(CelulaDupla *esq, CelulaDupla *dir, int*comp) {
    Jogador pivo = dir->elemento;
    CelulaDupla *i = esq->ant;

    for (CelulaDupla *j = esq; j != dir; j = j->prox) {

        if (strcmp(j->elemento.estadoNascimento, pivo.estadoNascimento) < 0 || 
        (strcmp(j->elemento.estadoNascimento, pivo.estadoNascimento) == 0 && strcmp(j->elemento.nome, pivo.nome) <= 0)) 
        {
            (*comp)++;
            i = (i == NULL) ? esq : i->prox;
            swap(i, j);
        }
    }

    i = (i == NULL) ? esq : i->prox;
    (*comp)++;
    swap(i, dir);
    return i;
}

void quicksort(CelulaDupla *esq, CelulaDupla *dir, int*comp) {
    if (dir != NULL && esq != dir && esq != dir->prox) {
        (*comp)++;
        CelulaDupla *pivo = slice(esq, dir, comp);
        quicksort(esq, pivo->ant, comp);
        quicksort(pivo->prox, dir, comp);
    }
}
//.........................................................................................................

int main() {
// .......................................PARTE 1.....................................

    FILE * arq = fopen("/tmp/players.csv", "r");
    //FILE * arq = fopen("C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv", "r");

    if(arq == NULL){
        printf("Falha ao abrir arquivo!\n");
        return 1;
    }
    ListaFlexDupla* totalJogadores = criarListaFlexDupla();
    
    char linha[1001];
    fgets(linha, sizeof(linha), arq);

    while(fgets(linha, sizeof(linha), arq) != NULL) 
    {   
        inserirFim(totalJogadores, ler(linha, strlen(linha)));
    }
    fclose(arq);

// .....................................PARTE 2......................................
    ListaFlexDupla* lista = criarListaFlexDupla();

    char* entrada = malloc(55*sizeof(char));
    while(strcmp(entrada, "FIM") != 0){         
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0){
            int id = atoi(entrada);
            inserirFim(lista, procurarJogadorPorId(totalJogadores, id));
        }
    }
    free(entrada);

    // ------ LOG ------------------
    clock_t start_time, end_time;
    double time;
    start_time = clock();
    int comparacoes = 0;
  // ------------------------------

    quicksort(lista->primeiro->prox, lista->ultimo, &comparacoes);
    mostrarCrescente(lista);
  // ------------------ ARQUIVO LOG ----------------------------------
    end_time = clock();
    time = ((double)(end_time - start_time)) / CLOCKS_PER_SEC;
    arq = fopen("matricula_quicksort2.txt", "w");
    fprintf(arq, "802717\t%f\t%d\n", time, comparacoes);
    return 0;
}