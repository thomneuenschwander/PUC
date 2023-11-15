#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <stdbool.h>

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
    printf("[%d] ## %s ## %d ## %d ## %d ## %s ## %s ## %s ##\n", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
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
//---------------------------- PILHA FLEXIVEL ----------------------------------------------------
typedef struct Celula {
    struct Celula* prox;
    struct Jogador elemento;
}Celula;

Celula* criarCelula(Jogador x) {
    Celula* novaCelula = malloc(sizeof(Celula));
    novaCelula->prox = NULL;
    novaCelula->elemento = x;
    return novaCelula;
}

typedef struct PilhaFlex {
    struct Celula* topo;
    int tamanho;
}PilhaFlex;

    // ------------- INICIALIZAR PILHA FLEX------------------
PilhaFlex* criarPilhaFlex(){
    PilhaFlex* novaPilha = malloc(sizeof(PilhaFlex));
    novaPilha->topo = NULL;
    novaPilha->tamanho = 0;
    return novaPilha;
}

//----------------------------FUNÇÕES PILHA FLEXIVEL ----------------------------------------------------

    // ------------- MOSTRAR ------------------
void mostrarDoTopo(PilhaFlex* pilha){
    Celula* i = pilha->topo;
    while (i != NULL)
    {
        imprimir(i->elemento);
        i = i->prox;
    }
}
void mostrarDoFundoREC(Celula*i){
    if(i != NULL) {
        mostrarDoFundoREC(i->prox);
        imprimir(i->elemento);
    }
}
void mostrarDoFundo(PilhaFlex* pilha){
    mostrarDoFundoREC(pilha->topo);
}

    // ------------- EMPILHAR ------------------
void empilhar(PilhaFlex* pilha, Jogador x){
    Celula*tmp = criarCelula(x);
    tmp->prox = pilha->topo;
    pilha->topo = tmp;
    pilha->tamanho++;
}

    // ------------- DESEMPILHAR ------------------
Jogador desempilhar(PilhaFlex* pilha){
    if(pilha->topo == NULL){Jogador error; error.id = -1; return error;}
    Jogador resp = pilha->topo->elemento;
    Celula*tmp = pilha->topo;
    pilha->topo = pilha->topo->prox;
    free(tmp);
    return resp;
}
    // ------------- DESALOCAR MEMORIA PILHA FLEX ------------------
void mdesalloc(PilhaFlex*pilha){
    Celula*i = pilha->topo;
    while (i != NULL)
    {
        Celula*delete = i;
        i = i->prox;
        free(delete);
    }
    free(pilha);
}
//----------------------------  MAIN    --------------------------------------------------------------------------
    // PROCURAR JOGADOR EM UM ARRAY ATRAVES DO ID
Jogador procurarJogadorPorId(Jogador*arr,int tam, int id) {
    for(int i = 0; i < tam; i++){
        if(arr[i].id == id){
            return arr[i];
        }
    }
    printf("Jogador nao encontrado!\n");
    Jogador error;
    error.id = -1;
    return error;
}
    // ALTERAR O ATRIBUTO ID PARA A POSIÇAO A PARTIR DO TOPO DA PILHA
void alterarIdParaPosicaoDoTopo(PilhaFlex*pilha){
    Celula*i = pilha->topo;
    int pos = 0;
    while (i != NULL) { i->elemento.id = pos++; i = i->prox; }
}
    // ALTERAR O ATRIBUTO ID PARA A POSIÇAO A PARTIR DO FUNDO DA PILHA
void alterarIdParaPosicaoDoFundoREC(Celula*i, int count){
    if(i != NULL) {
        alterarIdParaPosicaoDoFundoREC(i->prox, count-1);
        i->elemento.id = count;
    }
}
void alterarIdParaPosicaoDoFundo(PilhaFlex*pilha){
    int tam = (pilha->tamanho) - 1;
    alterarIdParaPosicaoDoFundoREC(pilha->topo, tam);
}
    // MOSTRAR ARRAY DE JOGADORES
void mostrarArrayJogadores(Jogador*arr,int tam){
    for(int i = 0; i < tam; i++){
        imprimir(*(arr + i));
    }
}
    // REALOCAR UMA STRING
char* realocar(char*str, int newTam){
    str = realloc(str, (newTam + 1)*sizeof(char));
    if (str == NULL) {
        fprintf(stderr, "Erro na alocação de memória.\n");
        exit(1);
    }
    return str;
}
    // INVERTE A POSICAO DOS JOGADORES DE UM ARRAY 
Jogador* arrJogadoresInvertido(Jogador* arr, int tam){
    Jogador* arrInvertido = malloc(tam*sizeof(Jogador));
    int index = 0;
    for(int i = tam-1; i >= 0; i--) {
        *(arrInvertido + index++) = *(arr + i);
    }
    free(arr);
    return arrInvertido;
}
    // EXTRAI O ID DA STRING DE ENTRADA 
int extrairID(char*linha, int tam){
    char pos[31];
    int index = 0;
    for(int i = 2; i <= tam-1; i++){
        pos[index++] = linha[i];
    }
    return atoi(pos);
}

int main() {
// .......................................PARTE 1.....................................
    FILE * arq = fopen("/tmp/players.csv", "r");
    //FILE * arq = fopen("C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv", "r");

    if(arq == NULL){
        printf("Falha ao abrir arquivo!\n");
        return 1;
    }

    Jogador* totalJogadores = malloc(3922*sizeof(Jogador));
    int index = 0;

    char linha[1001];
    fgets(linha, sizeof(linha), arq);
    while(fgets(linha, sizeof(linha), arq) != NULL) {   
        Jogador tmp = ler(linha, strlen(linha));
        *(totalJogadores + index++) = tmp;
    }
    

    PilhaFlex* pilha = criarPilhaFlex();
   
   char* entrada = malloc(55*sizeof(char));
    while(strcmp(entrada, "FIM") != 0){         
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0){
            int id = atoi(entrada);
            empilhar(pilha, procurarJogadorPorId(totalJogadores, index, id));
        }
    }
    alterarIdParaPosicaoDoFundo(pilha);
    free(entrada);
// .....................................PARTE 2......................................
    int n;
    scanf("%d", &n);

    for(int i = 0; i < n; i++)
    {
        char* entrada = malloc(55*sizeof(char));
        scanf(" %[^\n]", entrada);
        int entradaTam = strlen(entrada);
        entrada = realocar(entrada, entradaTam);

        if(entrada[0] == 'R')
        {
            printf("(R) %s\n", desempilhar(pilha).nome);
        }else if(entrada[0] == 'I')
        {
            int id = extrairID(entrada, entradaTam);
            empilhar(pilha, procurarJogadorPorId(totalJogadores, index, id));
        }
        free(entrada);
    }
    
    mostrarDoFundo(pilha);

  // desalocamento de memória
    free(totalJogadores);
    mdesalloc(pilha);
    fclose(arq);
    return 0;
}
