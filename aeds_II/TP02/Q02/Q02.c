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

void imprimir(Jogador jogador){
    printf("[");
    printf("%d ## %s ## %d ## %d ## %d ## %s ## %s ## %s", jogador.id, jogador.nome, jogador.altura, jogador.peso, jogador.anoNascimento, jogador.universidade, jogador.cidadeNascimento, jogador.estadoNascimento);
    printf("]\n");
}

Jogador buscarJogadorPorId(int id, Jogador * jogadores, int qnt_jg) {
    for(int i = 0; i < qnt_jg; i++){
        if(id == jogadores[i].id){
            return jogadores[i];
        }
    }
    Jogador jogadorNotFound;
    jogadorNotFound.id = -1;
    return jogadorNotFound;
}

Jogador ler(char*linha, int tam){
    Jogador jogador;

    int index[7];
        int virg = 0;
        for(int i = 0; i < tam; i++){
            if(linha[i] == ','){
                index[virg] = i;
                virg++;
            }
        }
    	
        
        int i = 0;  // index da linha
        int j = 0;  // index do vetor

        // armazenar o id
        char id[5];
        while(i < index[0]){
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
        while(i < index[1]){
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
        while(i < index[2]){
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
        while(i < index[3]){
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

        if((index[4]-1) - index[3]+1 != 1){
            while(i < index[4]){
                universidade[j] = linha[i];
                j++;
                i++;
            }
            universidade[j] = '\0';
        }else{
            strcpy(universidade, "nao informado");
        }
        strcpy(jogador.universidade, universidade);

        // armazenar ano nascimento
        i = index[4] + 1;
        j = 0;
        char anoNascimento[5];
        while(i < index[5]){
            anoNascimento[j] = linha[i];
            j++;
            i++;
        }
        anoNascimento[j] = '\0';
        int anoNascimento_integer = atoi(anoNascimento);
        jogador.anoNascimento = anoNascimento_integer;
        
        
        // armazenar cidade nascimento
        i = index[5] + 1;
        j = 0;
        char cidadeNascimento[31];

        if(index[6] - index[5] + 1 != 2){
            while(i < index[6]){
                cidadeNascimento[j] = linha[i];
                j++;
                i++;
            }
            cidadeNascimento[j] = '\0';
        }else{
            strcpy(cidadeNascimento, "nao informado");
        }
        strcpy(jogador.cidadeNascimento, cidadeNascimento);
        
        
        // armazenar estado nascimento
        i = index[6] + 1;
        j = 0;
        char estadoNascimento[31];
        if((tam-3) - index[6] + 1 != 0)
        {
            while(i < tam-1){
                estadoNascimento[j] = linha[i];
                j++;
                i++;
            }
            estadoNascimento[j] = '\0';
        }else{
            strcpy(estadoNascimento, "nao informado");
        }
        strcpy(jogador.estadoNascimento, estadoNascimento);

    return jogador;
}


int main(){
    FILE * arq = fopen("C:\\Users\\Pichau\\Desktop\\TP02\\Q02\\players.csv", "r");
    // FILE * arq = fopen("/tmp/players.csv", "r");

    char linha[1001];
    int qnt_jg = 3922;
    Jogador jg_vet[qnt_jg];
    int jg_index = 0;

    fgets(linha, sizeof(linha), arq); // Descartar a primeira linha
    while(fgets(linha, sizeof(linha), arq) != NULL){

        // printf("%s", linha);
        
        int tam = strlen(linha);
        
        // armazenar no array de jogadores
        jg_vet[jg_index] = ler(linha, tam);
        jg_index++;
    }
    

    char entrada[55];
    strcpy(entrada, "entrada");

    while(strcmp(entrada, "FIM") != 0){
        
        scanf("%s", entrada);
        if(strcmp(entrada, "FIM") != 0){
            int id_input = atoi(entrada);
            imprimir(buscarJogadorPorId(id_input, jg_vet, qnt_jg));
        }
    }
    
    

    fclose(arq);
}