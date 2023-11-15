import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import utils.CelulaSimples;
import utils.Jogador;

//----------------------------------------------------------------- PILHA FLEXIVEL ----------------------------------------------------
class PilhaFlex {
    private CelulaSimples topo;
    private int tamanho;
    public PilhaFlex(){
        topo = null;
        tamanho = 0;
    }
  //----------------------------------------- MOSTRAR ----------------------------------------------------------------------------
    public void mostrarDoTopo(){
        CelulaSimples i = topo;
        while (i != null) {
            i.getElemento().imprimir();
            i = i.getProx();
        }
    }
    private void mostrarDoFundo(CelulaSimples i){
        if(i != null) {
           mostrarDoFundo(i.getProx());
           i.getElemento().imprimir();
        }
    }
    public void mostrarDoFundo(){
        mostrarDoFundo(topo);
    }
  //----------------------------------------- EMPILHAR ----------------------------------------------------------------------------
    public void empilhar(Jogador x){
        CelulaSimples tmp = new CelulaSimples(x);
        tmp.setProx(topo);
        topo = tmp;
        tamanho++;
        tmp = null;
    }
  //----------------------------------------- DESEMPILHAR ----------------------------------------------------------------------------
    public Jogador desempilhar(){
        Jogador resp = topo.getElemento();
        CelulaSimples tmp = topo;
        topo = topo.getProx();
        tamanho--;
        tmp.setProx(null);
        tmp = null;
        return resp;
    }
  //----------------------------------------- OUTROS ----------------------------------------------------------------------------
    public Jogador procurarJogadorPorId(int id){
        CelulaSimples i = topo;
        while (i != null) {
            if(i.getElemento().getId() == id) {
                return i.getElemento();
            }
            i = i.getProx();
        }
        return null;
    }
    public int tamanho(){
        return tamanho;
    }
    public void alterarIdParaPosicao(){
        CelulaSimples i = topo;
        int pos = 0;
        while(i != null) {
            i.getElemento().setId(pos++);
            i = i.getProx();
        }
    }
    public Boolean isEmpty(){
        return tamanho == 0;
    }
}
public class Q6 {
    public static PilhaFlex deUmaPilhaFlexParaOutra(PilhaFlex p1){
        PilhaFlex p2 = new PilhaFlex();
        while(!p1.isEmpty()){
            p2.empilhar( p1.desempilhar() );
        }
        return p2;
    }

    public static void main(String[] args) {
// ..................................................PARTE 1..............................................
        String path = "C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv";
        //String path = "/tmp/players.csv";

        PilhaFlex totalJogadores = new PilhaFlex();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while(br.ready()){
                Jogador tmp = new Jogador();
                tmp.ler(br.readLine());
                totalJogadores.empilhar(tmp);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);

        PilhaFlex pilha = new PilhaFlex();
        
        String entrada = "in";
        while (!entrada.equals("FIM")) {
            entrada = sc.nextLine();
            if (!entrada.equals("FIM")) {
                int id = Integer.parseInt(entrada);
                pilha.empilhar(totalJogadores.procurarJogadorPorId(id));
            }
        }
// ..................................................PARTE 2.............................................. 
        int n = sc.nextInt(); sc.nextLine();
        for(int i = 0; i < n; i++) {
            String linha = sc.nextLine();
            if(linha.contains("I")){
                String[] linhaSplit = linha.split(" ");
                int id = Integer.parseInt(linhaSplit[1]);
                pilha.empilhar(totalJogadores.procurarJogadorPorId(id));
            }else if(linha.equals("R")){
                System.out.println("(R) "+pilha.desempilhar().getNome());
            }
        }
        pilha = deUmaPilhaFlexParaOutra(pilha);
        pilha.alterarIdParaPosicao();
        pilha.mostrarDoTopo();
        sc.close();
    }
}

