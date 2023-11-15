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
//----------------------------LISTA SEQUENCIAL----------------------------------------------------
typedef struct ListaSequencial {
    Jogador*arr;
    int n;
}ListaSequencial;
//----------------------------FUNÇÕES LISTA SEQUENCIAL----------------------------------------------------
ListaSequencial* criarListaSequencial(int tamMax){
    ListaSequencial*newLista = malloc(sizeof(ListaSequencial));
    newLista->arr = malloc(tamMax*sizeof(Jogador));
    newLista->n = 0;
    return newLista;
}
void mostrarCrecente(ListaSequencial*lista){
    for(int i = 0; i < lista->n; i++) {
        imprimir(lista->arr[i]);
    }
}
void mostrarDecrecente(ListaSequencial*lista){
    for(int i = lista->n-1; i >= 0 ; i--) {
        imprimir(lista->arr[i]);
    }
}
void inserirFim(ListaSequencial*lista, Jogador x){
    lista->arr[lista->n] = x;
    lista->n++;
}
void inserirInicio(ListaSequencial*lista, Jogador x){
    int tam = lista->n;
    for(int i = tam; i > 0; i--) {
        lista->arr[i] = lista->arr[i-1];
    }
    lista->arr[0] = x;
    lista->n++;
}
void inserir(ListaSequencial*lista, Jogador x, int pos) {
    if(pos == 0) {inserirInicio(lista, x);}
    else if(pos == lista->n) {inserirFim(lista, x);}
    else {
        int tam = lista->n;
        for(int i = tam; i > pos; i--){
            lista->arr[i] = lista->arr[i-1];
        }
        lista->arr[pos] = x;
        lista->n++;
    }
}
Jogador removerFim(ListaSequencial*lista){
    int tam = lista->n;
    Jogador res = lista->arr[tam-1];
    lista->n--;
    return res;
}
Jogador removerInicio(ListaSequencial*lista){
    int tam = lista->n;
    Jogador resp = lista->arr[0];
    for(int i = 0; i < tam-1; i++){
        lista->arr[i] = lista->arr[i+1];
    }
    lista->n--;
    return resp;
}
Jogador remover(ListaSequencial*lista, int pos){
    Jogador resp;
    int tam = lista->n;
    if(pos == 0){resp = removerInicio(lista);}
    else if(pos == tam-1){resp = removerFim(lista);}
    else {
        resp = lista->arr[pos];
        for(int i = pos; i < tam-1; i++){
            lista->arr[i] = lista->arr[i+1]; 
        }
        lista->n--;
    }
    return resp;
}
void alterarIdParaPosicao(ListaSequencial*lista){
    int tam = lista->n;
    for(int i = 0; i <= tam-1; i++){
        lista->arr[i].id = i;
    }
}
//----------------------------  MAIN    --------------------------------------------------------------------------
Jogador procurarJogadorPorId(ListaSequencial*lista, int id) {
    int tam = lista->n;
    for(int i = 0; i < tam; i++) {
        if(lista->arr[i].id == id) {
            return lista->arr[i];
        }
    }
    printf("Jogador nao encontrado!\n");

    Jogador error;
    error.id = -1;
    return error;
}
char* realocar(char*str, int newTam){
    str = realloc(str, (newTam + 1)*sizeof(char));
    if (str == NULL) {
        fprintf(stderr, "Erro na alocação de memória.\n");
        exit(1);
    }
    return str;
}
int extrairN2(char*linha, int tam){
    char pos[31];
    int index = 0;
    for(int i = 3; i <= tam-1; i++){
        pos[index++] = linha[i];
    }
    return atoi(pos);
}
int extrairN3(char*linha, int tam){
    int posSegundoEsp = 0;
    for(int i = 0; i < tam; i++){
        if(linha[i] == ' ') {
            posSegundoEsp = i;
        }
    }
    char*idChar = malloc(tam*sizeof(char));
    int index = 0;
    for(int i = posSegundoEsp+1; i < tam; i++){
        idChar[index++] = linha[i];
    }
    idChar[index] = '\0';
    return atoi(idChar);
}
int main() {
// .......................................PARTE 1.....................................
    //FILE * arq = fopen("/tmp/players.csv", "r");
    FILE * arq = fopen("C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv", "r");

    if(arq == NULL){
        printf("Falha ao abrir arquivo!\n");
        return 1;
    }

    ListaSequencial* totalJogadores = criarListaSequencial(3922);
    char linha[1001];
    fgets(linha, sizeof(linha), arq);
    while(fgets(linha, sizeof(linha), arq) != NULL) {   // armazenamento de todos jogadores em uma lista sequencial
        Jogador tmp = ler(linha, strlen(linha));
        inserirFim(totalJogadores, tmp);
    }

    ListaSequencial* lista = criarListaSequencial(3922);

    char* entrada = malloc(55*sizeof(char));
    while(strcmp(entrada, "FIM") != 0){         // armazenamento de jogadores selecionados por id em uma lista sequencial
        scanf(" %[^\n]", entrada);
        if(strcmp(entrada, "FIM") != 0){
            int id = atoi(entrada);
            inserirFim(lista, procurarJogadorPorId(totalJogadores, id));
        }
    }
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
            if(entrada[1] == 'I'){
                Jogador removidoInicio = removerInicio(lista);
                printf("(R) %s\n", removidoInicio.nome);
            }else if(entrada[1] == 'F'){
                Jogador removidoFim = removerFim(lista);
                printf("(R) %s\n", removidoFim.nome);
            }else{
                int pos = extrairN2(entrada, entradaTam);
                printf("(R) %s\n", remover(lista, pos).nome);
            }
        }else if(entrada[0] == 'I')
        {
            if(entrada[1] == 'I'){  // II 879
                int id = extrairN2(entrada, entradaTam);
                
                inserirInicio(lista, procurarJogadorPorId(totalJogadores, id));
            }else if(entrada[1] == 'F'){ // IF 971
                int id = extrairN2(entrada, entradaTam);
                inserirFim(lista, procurarJogadorPorId(totalJogadores, id));
            }else {      // I* 20 816
                int pos = extrairN2(entrada, entradaTam);
                int id = extrairN3(entrada, entradaTam);
                inserir(lista, procurarJogadorPorId(totalJogadores, id), pos);
            }
        }
        free(entrada);
    }
    alterarIdParaPosicao(lista);
    mostrarCrecente(lista);

  // desalocamento de memória
    free(lista);
    free(totalJogadores);
    fclose(arq);
    return 0;
}
