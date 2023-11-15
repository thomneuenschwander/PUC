import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
class CelulaDupla {
    private CelulaDupla prox;
    private CelulaDupla ant;
    private Jogador elemento;
    
    public CelulaDupla() {
        this.prox = null;
        this.ant = null;
        this.elemento = null;
    }
    public CelulaDupla(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.ant = null;
    }
    public CelulaDupla getProx() {
        return prox;
    }
    public void setProx(CelulaDupla prox) {
        this.prox = prox;
    }
    public CelulaDupla getAnt() {
        return ant;
    }
    public void setAnt(CelulaDupla ant) {
        this.ant = ant;
    }
    public Jogador getElemento() {
        return elemento;
    }
    public void setElemento(Jogador elemento) {
        this.elemento = elemento;
    }
    
}

class Jogador{
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private String anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador(){
        id = 0;
        nome = "";
        altura = 0;
        peso = 0;
        universidade = "";
        anoNascimento = "";
        cidadeNascimento = "";
        estadoNascimento = "";
    }
    public Jogador(int id,String nome,int altura, int peso,String universidade, String anoNascimento, String cidadeNascimento,String estadoNascimento){
        this.id = id;
        this.nome = nome;
        this.altura = altura;
        this.peso = peso;
        this.universidade = universidade;
        this.anoNascimento = anoNascimento;
        this.cidadeNascimento = cidadeNascimento;
        this.estadoNascimento = estadoNascimento;
        
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public int getAltura() {
        return altura;
    }
    public void setAltura(int altura) {
        this.altura = altura;
    }
    public int getPeso() {
        return peso;
    }
    public void setPeso(int peso) {
        this.peso = peso;
    }
    public String getUniversidade() {
        return universidade;
    }
    public void setUniversidade(String universidade) {
        this.universidade = universidade;
    }
    public String getAnoNascimento() {
        return anoNascimento;
    }
    public void setAnoNascimento(String anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    public String getEstadoNascimento() {
        return estadoNascimento;
    }
    public void setEstadoNascimento(String estadoNascimento) {
        this.estadoNascimento = estadoNascimento;
    }
    public String getCidadeNascimento() {
        return cidadeNascimento;
    }
    public void setCidadeNascimento(String cidadeNascimento) {
        this.cidadeNascimento = cidadeNascimento;
    }
    public void ler(String linha){
        String[] data = new String[8];
        int tmp = 0;
        int j = 0;
        for(int i = 0; i < linha.length(); i++) {
        	if(linha.charAt(i) == ',') {
        		data[j] = linha.substring(tmp, i);
        		tmp = i + 1;
        		if(data[j].intern() == "") {
        			data[j] = "nao informado";
        		}
        		j++;
        	}
        }
        data[7] = linha.substring(tmp, linha.length());
        if(data[7].intern() == ""){
            data[7] = "nao informado";
        }

        setId(Integer.parseInt(data[0]));
        setNome(data[1]);
        setAltura(Integer.parseInt(data[2]));
        setPeso(Integer.parseInt(data[3]));
        setUniversidade(data[4]);
        setAnoNascimento(data[5]);
        setEstadoNascimento(data[6]);
        setCidadeNascimento(data[7]);
    }
    public void imprimir() {
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## "
                + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getEstadoNascimento() + " ## "
                + getCidadeNascimento() + "]");
    }
    public Jogador clone(){
        Jogador jogadorClone = new Jogador();
        jogadorClone.id = this.id;
        jogadorClone.nome = this.nome;
        jogadorClone.altura = this.altura;
        jogadorClone.peso = this.peso;
        jogadorClone.universidade = this.universidade;
        jogadorClone.anoNascimento = this.anoNascimento;
        jogadorClone.estadoNascimento = this.estadoNascimento;
        return jogadorClone;
    }
}


class Lista {
    private CelulaDupla primeiro;
    private CelulaDupla ultimo;
    private int tamanho;
    public Lista (){
        primeiro = new CelulaDupla();
        ultimo = primeiro;
        tamanho = 0;
    }
    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }
  //----------------------------------------- MOSTRAR ----------------------------------------------------------------------------
    public void mostrarCrescente(){
        CelulaDupla i = primeiro.getProx();
        while(i != null){
            i.getElemento().imprimir();
            i = i.getProx();
        }
    }
    public void mostrarDecrescente(){
        CelulaDupla i = ultimo;
        while(i != primeiro){
            i.getElemento().imprimir();
            i = i.getAnt();
        }
    }
  //----------------------------------------- INSERIR ----------------------------------------------------------------------------
    public void inserirInicio(Jogador x){
        if(primeiro == ultimo){
            primeiro.setProx(new CelulaDupla(x));
            primeiro.getProx().setAnt(primeiro);
            ultimo = primeiro.getProx();
        }else{
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.setAnt(primeiro);
            tmp.setProx(primeiro.getProx());
            primeiro.getProx().setAnt(tmp);
            primeiro.setProx(tmp);
            tmp = null;
        }
        tamanho++;
    }
    public void inserir(Jogador x, int pos) throws Exception{
        if(pos < 0 || pos > tamanho){throw new Exception("Posicao invalida!");}
        else if(pos == 0){inserirInicio(x);}
        else if(pos == tamanho){inserirFim(x);}
        else{
            CelulaDupla i = primeiro.getProx();
            for(int j = 0; j < pos; j++, i = i.getProx());
            CelulaDupla tmp = new CelulaDupla(x);
            tmp.setAnt(i);
            tmp.setProx(i.getProx());
            i.getProx().setAnt(tmp);
            i.setProx(tmp);
            i = tmp = null;
            tamanho++;
        }
    }
    public void inserirFim(Jogador x){
        ultimo.setProx(new CelulaDupla(x));
        ultimo.getProx().setAnt(ultimo);
        ultimo = ultimo.getProx();
        tamanho++;
    }
  //----------------------------------------- REMOVER ----------------------------------------------------------------------------
    public Jogador removerInicio() throws Exception{
        if(primeiro == ultimo) {throw new Exception("Lista ja esta vazia!");}
        Jogador res = primeiro.getProx().getElemento();
        CelulaDupla tmp = primeiro;
        primeiro = primeiro.getProx();
        primeiro.setAnt(null);
        tmp.setProx(null);
        tmp = null;
        tamanho--;
        return res;
    }
    public Jogador remover(int pos) throws Exception {
        Jogador res = null;
        if(primeiro == ultimo) {throw new Exception("Lista ja esta vazia!");}
        else if(pos < 0 || pos >= tamanho){throw new Exception("Posicao invalida!");}
        else if(pos == 0){res = removerInicio();}
        else if(pos == tamanho-1){res = removerFim();}
        else {
            CelulaDupla i = primeiro.getProx();
            for(int j = 0; j <= pos; j++, i = i.getProx());
            res = i.getElemento();
            i.getAnt().setProx(i.getProx());
            i.getProx().setAnt(i.getAnt());
            i.setAnt(null);
            i.setProx(null);
            i = null;
            tamanho--;
        }
        return res;
    }
    public Jogador removerFim() throws Exception{
        if(primeiro == ultimo) {throw new Exception("Lista ja esta vazia!");}
        Jogador res = ultimo.getElemento();
        CelulaDupla tmp = ultimo;
        ultimo = ultimo.getAnt();
        ultimo.setProx(null);
        tmp.setAnt(null);
        tmp = null;
        tamanho--;
        return res;
    }
  //----------------------------------------- OUTROS ----------------------------------------------------------------------------
    public Jogador procurarJogadorPorId(int id) {
        CelulaDupla i = primeiro.getProx();
        while(i != null){
            if(i.getElemento().getId() == id){
                return i.getElemento();
            }
            i = i.getProx();
        }
        return null;
    }
    public void alterarIdParaPosicao(){
        CelulaDupla i = primeiro.getProx();
        int count = 0;
        while (i != null) {
            i.getElemento().setId(count);
            count++;
            i = i.getProx();
        }
    }
    public String[] nomesJogadores(){
        String[] arr = new String[tamanho];
        int index = 0;
        CelulaDupla i = primeiro.getProx();
        while(i != null){
            arr[index++] = i.getElemento().getNome();
            i = i.getProx();
        }
        return arr;
    }
    public void selectionSortId(){
        for(CelulaDupla i = primeiro.getProx(); i != null; i = i.getProx()){
            for(CelulaDupla j = i.getProx(); j != null; j = j.getProx()){
                if(i.getElemento().getId() > j.getElemento().getId()) {
                    Jogador tmp = i.getElemento();
                    i.setElemento(j.getElemento());
                    j.setElemento(tmp);
                }
            }
        }
    }
    public Jogador getJogador(int index){
        CelulaDupla i = primeiro;
        for(int j = 0; j <= index; j++, i = i.getProx());
        return i.getElemento();
    }
    public CelulaDupla getCelulaDupla(int index){
        CelulaDupla i = primeiro;
        for(int j = 0; j <= index; j++, i = i.getProx());
        return i;
    }
    public Jogador getUltimoJogador(){
        return ultimo.getElemento();
    }

  //----------------------------------------- AUX ----------------------------------------------------------------------------
    public void selectionSortEstadoDeNascimento(){
        for(CelulaDupla i = primeiro.getProx(); i != null; i = i.getProx()){
            for(CelulaDupla j = i.getProx(); j != null; j = j.getProx()){

                if(comparacao(i, j) > 0) {
                    Jogador tmp = i.getElemento();
                    i.setElemento(j.getElemento());
                    j.setElemento(tmp);
                }
            }
        }
    }
    private void swap(CelulaDupla x, CelulaDupla y){
        Jogador tmp = x.getElemento();
        x.setElemento(y.getElemento());
        y.setElemento(tmp);
    }
    private int comparacao(CelulaDupla i, CelulaDupla j){
        return i.getElemento().getEstadoNascimento().compareTo(j.getElemento().getEstadoNascimento());
    }
    private int comparacao(CelulaDupla i, Jogador j){
        return i.getElemento().getEstadoNascimento().compareTo(j.getEstadoNascimento());
    }
  //----------------------------------------- QUICKSORT DE JOGADORES ----------------------------------------------------------------------------
    private CelulaDupla slice(CelulaDupla esq, CelulaDupla dir){
        Jogador piv = dir.getElemento();
        CelulaDupla i = esq.getAnt();

        for(CelulaDupla j = esq; j != dir; j = j.getProx()) 
        {
            if(comparacao(i, piv) < 0 || comparacao(i, piv) == 0)
            {
                i = (i == null) ? esq : i.getProx();
                swap(i, j);
            }
        }
        i = (i == null) ? esq : i.getProx();
        swap(dir, i);
        return i;
    }
    
    private void quickSort(CelulaDupla esq, CelulaDupla dir){
        if(dir != null && esq != null && esq != dir.getProx()) {
            CelulaDupla piv = slice(esq, dir);
            quickSort(esq, piv.getAnt());
            quickSort(piv.getProx(), dir);
        }
    }
    public void quickSort(){
        quickSort(primeiro.getProx(), ultimo);
    }

  // ------------------ HARD CODE SORT -------------------------------------------
    public Jogador[] listaToArr(){
        Jogador[] arr = new Jogador[tamanho];
        int index = 0;
        CelulaDupla i = primeiro.getProx();
        while(i != null){
            arr[index++] = i.getElemento();
            i = i.getProx();
        }
        return arr;
    }
    public Lista hardCodeSort(){
        Jogador[] arr = listaToArr();
        for(int i = 0; i < arr.length; i++){
            for(int j = (i+1); j < arr.length; j++){
                if(arr[i].getEstadoNascimento().compareTo(arr[j].getEstadoNascimento()) > 0){
                    Jogador tmp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
        Lista sorted = new Lista();
        for(Jogador jg : arr){
            sorted.inserirFim(jg);
        }
        return sorted;
    }
}


class Q11 {
    
    public static void main(String[] args) throws Exception {
// ..................................................PARTE 1..............................................
        //String path = "J:\\TP03\\anunciado\\players.csv";
        //String path = "C:\\Users\\Pichau\\Desktop\\TP03\\anunciado\\players.csv";
        String path = "/tmp/players.csv";

        Lista totalJogadores = new Lista();

        try (BufferedReader br = new BufferedReader(new FileReader(path))){
            br.readLine();
            while(br.ready()){
                Jogador tmp = new Jogador();
                tmp.ler(br.readLine());
                totalJogadores.inserirFim(tmp);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);

        Lista lista = new Lista();

        String entrada = "in";
        while (!entrada.equals("FIM")) {
            entrada = sc.nextLine();
            if (!entrada.equals("FIM")) {
                int id = Integer.parseInt(entrada);
                lista.inserirFim(totalJogadores.procurarJogadorPorId(id));
            }
        }
        Lista sorted = lista.hardCodeSort();
        sorted.mostrarCrescente();
        sc.close();
    }
}