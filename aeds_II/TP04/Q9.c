#include <stdio.h>
#include <stdlib.h>
#include <string.h>

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

//-------------------------------------------------- LISTA SIMPLES ---------------------------------------------------------

//-------------------------------------------------- CELULA
typedef struct Cell {
    struct Jogador elemento;
    struct Cell* next;
}Cell;

Cell* createHead(){
    Cell* nova = malloc(sizeof(Cell));
    nova->next = NULL;
    return nova;
}
Cell* createCell(Jogador x){
    Cell* nova = malloc(sizeof(Cell));
    nova->next = NULL;
    nova->elemento = x;
    return nova;
}
//-------------------------------------------------- LISTA
typedef struct ListaFlex {
    int tamanho;
    struct Cell* head,* tail;
}ListaFlex;

ListaFlex* createLista(){
    ListaFlex* nova =malloc(sizeof(ListaFlex));
    nova->head = createHead();
    nova->tail = nova->head;
    nova->tamanho = 0;
    return nova;
}
void inserirCell(Jogador x, ListaFlex* lista){
    lista->tail->next = createCell(x);
    lista->tail = lista->tail->next;
    lista->tamanho++;
}
//-------------------------------------------------- ---------------- ---------------------------------------------------------

typedef struct HashTable {
    struct ListaFlex** table;
    int tamTabela;
}HashTable;

HashTable* createHashTable(){
    HashTable* hash = malloc(sizeof(HashTable));

    hash->tamTabela = 25;

    hash->table = malloc(25*sizeof(ListaFlex*));
    for(int i = 0; i < 25; i++){
        hash->table[i] = createLista();
    }
    return hash;
}

int hash(Jogador x){
    return x.altura % 25;
}

void inserir(Jogador x, HashTable* tabela) {
    int pos = hash(x);
    if(tabela->table[pos]->head->next == NULL){

        inserirCell(x, tabela->table[pos]);
    }else{

        inserirCell(x, tabela->table[pos]);
    }
}

void mostrar(HashTable* tabela){
    for(int i = 0; i < 25; i++) {
        Cell* j = tabela->table[i]->head->next;
        printf("[");
        while (j != NULL)
        {
            printf("%d ", j->elemento.id);
            j = j->next;
        }
        
        printf("]\n");
    }
}
int pesquisar(Jogador x, HashTable* table){
    int pos = hash(x);

    if(table->table[pos]->head->next != NULL){
        for(Cell* i = table->table[pos]->head->next; i != NULL; i = i->next){
            if(strcmp(i->elemento.nome, x.nome) == 0){
                return 1;
            }
        }
    }
    return 0;
}

//-------------------------------------------------- MAIN ---------------------------------------------------------
void showPlayersArr(Jogador*arr,int tam){
    for(int i = 0; i < tam; i++){
        imprimir(*(arr + i));
    }
}
Jogador searchPlayerInArr(Jogador*arr,int size, int id) {
    for(int i = 0; i < size; i++){
        if(arr[i].id == id){
            return arr[i];
        }
    }
    printf("404!\n");
    Jogador error;
    error.id = -1;
    return error;
}

Jogador pesquisarPorNome(Jogador*arr,int size, char* name) {
    for(int i = 0; i < size; i++){

        if(strcmp(arr[i].nome, name) == 0){
            return arr[i];
        }
    }

    Jogador error;
    error.id = -1;
    return error;
}
int main() {
// ....................................... PART 1.............................................
    //FILE * arq = fopen("C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv", "r");
    FILE * arq = fopen("/tmp/players.csv", "r");
    
    if(arq == NULL){
        printf("Falha ao abrir arquivo!\n");
        return 1;
    }

    Jogador* totalPlayers = malloc(3922*sizeof(Jogador));
    int index = 0;

    char line[1001];
    fgets(line, sizeof(line), arq);
    while(fgets(line, sizeof(line), arq) != NULL) {   
        Jogador tmp = ler(line, strlen(line));
        *(totalPlayers + index++) = tmp;
    }

// ....................................... PART 2.......................................... 

    HashTable* table = createHashTable();

    while(strcmp(line, "FIM") != 0)
    {         
        scanf(" %[^\n]", line);
        if(strcmp(line, "FIM") != 0)
        {
            int id = atoi(line);
            Jogador found = searchPlayerInArr(totalPlayers, index, id);
            inserir(found, table);
        }
    }

    // ....................................... PART 3.......................................... 

    scanf(" %[^\n]", line);
    while(strcmp(line, "FIM") != 0)
    {
        Jogador found = pesquisarPorNome(totalPlayers, index, line);

        if(found.id != -1)
        {
            if(pesquisar(found, table)){
                printf("%s SIM\n", found.nome);
            }else{
                printf("%s NAO\n", found.nome);
            }
        }

        scanf(" %[^\n]", line);
    }

    return 0;
}