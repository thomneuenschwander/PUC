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

//-------------------------------------------------- BINARY TREE AVL ---------------------------------------------------------
typedef struct Node
{
    int height;
    struct Node *left, *right;
    struct Jogador element;
} Node;

Node *createNode(Jogador x)
{
    Node *node = malloc(sizeof(Node));
    node->left = node->right = NULL;
    node->height = 0;
    node->element = x;
    return node;
}

//-------------------------------------------------- BALANCE ---------------------------------------------------------

//------------------------------- NODE LEVEL ------------------------------
int max(int a, int b){
    return (a > b)? a : b;
}

int nodeHeight(Node* no) {
    if(no == NULL) {
        return -1;
    }else {
        return no->height;
    }
}

int factorBalance(Node* no) {
    if(no){
        return (nodeHeight(no->right) - nodeHeight(no->left));
    }else {
        return 0;
    }
}

//------------------------------- LEFT ROTATE -----------------------------
Node *leftRotate(Node *no)
{
    Node *right = no->right;
    Node *rightLeft = right->left;

    right->left = no;
    no->right = rightLeft;

    no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
    right->height = max(nodeHeight(right->left), nodeHeight(right->right))+1;

    return right;
}

//------------------------------- RIGHT ROTATE -----------------------------
Node *rightRotate(Node *no)
{
    Node *left = no->left;
    Node *leftRight = left->right;

    left->right = no;
    no->left = leftRight;

    no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
    left->height = max(nodeHeight(left->left), nodeHeight(left->right))+1;

    return left;
}
//------------------------- LEFT RIGHT ROTATE -------------------------------
Node *leftRightRotate(Node *no)
{
    no->left = leftRotate(no->left);
    return rightRotate(no);
}

//------------------------- RIGHT LEFT ROTATE -------------------------------
Node *rightLeftRotate(Node *no)
{
    no->right = rightRotate(no->right);
    return leftRotate(no);
}

Node *balance(Node *no)
{
    if (no != NULL)
    {
        int fb = factorBalance(no);

        if (fb > -2 && fb < 2)
        { // is already balance
            no->height = max(nodeHeight(no->left), nodeHeight(no->right))+1;
        }
        else if (fb == 2) // is unbalanced to the right
        {
            int childFb = factorBalance(no->right);

            if (childFb <= -1) // the child is unbalanced to the left
            {
                no->right = rightRotate(no->right);
            }
            no = leftRotate(no);
        }
        else // is unbalanced to the left
        {
            int childFb = factorBalance(no->left);

            if (childFb >= 1)
            {
                no->left = leftRotate(no->left);
            }
            no = rightRotate(no);
        }
    }

    return no;
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------

int comparisonKey(char* name, Node *i){
    return strcmp(name, i->element.nome);
}

//------------------------------------------------------------ INSERT ------------------------------------------------------------
Node *insert(Jogador x, Node *i)
{
    if (i == NULL)
    {
        return createNode(x);
    }
    else{
        if(comparisonKey(x.nome, i) < 0) {
            i->left = insert(x, i->left);
        }else {
            i->right = insert(x, i->right);
        }
    }
    i->height = max(nodeHeight(i->left), nodeHeight(i->right))+1;

    i = balance(i);

    return i;
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------
//------------------------------------------------------------ SEARCH PLAYER BY NAME ----------------------------------------------------------
int search(char* playerName, Node *i){
    if(i == NULL) {
        return 0;
    }
    else if(comparisonKey(playerName, i) == 0) {
        return 1;
    }
    else if(comparisonKey(playerName, i) < 0) {
        printf(" esq");
        return search(playerName, i->left);
    }
    else{
        printf(" dir");
        return search(playerName, i->right);
    }
}

///-------------------------------------------------- ---------------------- -----------------------------------------------------
//------------------------------------------------------------ TRAVERSAL ------------------------------------------------------------

//------------------------------- IN ORDER ------------------------------
void inOrder(Node *i)
{
    if (i != NULL)
    {
        inOrder(i->left);
        printf("%s (FB: %d); ", i->element.nome, factorBalance(i));
        inOrder(i->right);
    }
}
///-------------------------------------------------- ---------------------- -----------------------------------------------------
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

    Node* root = NULL;

    while(strcmp(line, "FIM") != 0)
    {         
        scanf(" %[^\n]", line);
        if(strcmp(line, "FIM") != 0)
        {
            int id = atoi(line);
            Jogador found = searchPlayerInArr(totalPlayers, index, id);
            root = insert(found, root);
        }
    }
// ....................................... PART 3.......................................... 

    scanf(" %[^\n]", line);
    while(strcmp(line, "FIM") != 0)
    {
        printf("%s raiz", line);

        if(search(line, root))
        {
            printf(" SIM\n");
        }else {
            printf(" NAO\n");
        }
        scanf(" %[^\n]", line);
    }

    return 0;
}