import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Jogador {
    private int id;
    private String nome;
    private int altura;
    private int peso;
    private String universidade;
    private String anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0;
        nome = "";
        altura = 0;
        peso = 0;
        universidade = "";
        anoNascimento = "";
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, String anoNascimento,
            String cidadeNascimento, String estadoNascimento) {
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

    public void ler(String linha) {
        String[] data = new String[8];
        int tmp = 0;
        int j = 0;
        for (int i = 0; i < linha.length(); i++) {
            if (linha.charAt(i) == ',') {
                data[j] = linha.substring(tmp, i);
                tmp = i + 1;
                if (data[j].intern() == "") {
                    data[j] = "nao informado";
                }
                j++;
            }
        }
        data[7] = linha.substring(tmp, linha.length());
        if (data[7].intern() == "") {
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

    public Jogador clone() {
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

class Nodle {
    private Jogador element;
    private Nodle left, right;

    public Nodle() {
        this.element = null;
        this.left = this.right = null;
    }

    public Nodle(Jogador element) {
        this.element = element;
        this.left = this.right = null;
    }
    
    public Nodle getLeft() {
        return left;
    }

    public void setLeft(Nodle left) {
        this.left = left;
    }

    public Nodle getRight() {
        return right;
    }

    public void setRight(Nodle right) {
        this.right = right;
    }

    public Jogador getElement() {
        return element;
    }

    public void setElement(Jogador element) {
        this.element = element;
    }
}
class BinarySearchTree {
    private Nodle root;
    public int comparisons;

    public BinarySearchTree(){
        root = null;
        comparisons = 0;
    }

    public Nodle getRoot() {
        return root;
    }

    public void setRoot(Nodle root) {
        this.root = root;
    }
    private int keyComparator(String name, Nodle i){
        return name.compareTo(i.getElement().getNome());
    }
    private Nodle insert(Nodle i, Jogador x) throws Exception {
        if(i == null){
            comparisons++;
            i = new Nodle(x);
        }
        else if(keyComparator(x.getNome(),  i) < 0)
        {
            comparisons++;
            i.setLeft(insert(i.getLeft(), x));
        }
        else if(keyComparator(x.getNome(),  i) > 0)
        {
            comparisons++;
            i.setRight(insert(i.getRight(), x));
        }
        else { throw new Exception("A BST cannot have elements with the same key"); }
        return i;
    }
    public void insert(Jogador x) throws Exception{
        setRoot(insert(getRoot(), x));
    }
  //-------------------------------------------------TRAVERSAL---------------------------------------------------
    private void preOrder(Nodle i) {
        if(i != null) {
            i.getElement().imprimir();
            preOrder(i.getLeft());
            preOrder(i.getRight());
        }
    }
    public void preOrder(){
        preOrder(root);
    }
    private void inOrder(Nodle i) {
        if(i != null) {
            inOrder(i.getLeft());
            System.out.println(i.getElement().getNome()+" ");
            inOrder(i.getRight());
        }
    }
    public void inOrder(){
        inOrder(root);
    }
    private String search(Nodle i, String name, String path) throws Exception {
        if(i == null) {
            path += " NAO";
            return path;
        }else if(keyComparator(name, i) == 0) {
            path += " SIM";
            return path;
        }
        else if (keyComparator(name, i) < 0) {
            path += " esq";
            return search(i.getLeft(), name, path);
        }else if (keyComparator(name, i) > 0) {
            path += " dir";
            return search(i.getRight(), name, path);
        }
        else { throw new Exception("A BST cannot have elements with the same key"); }
    }
    public String search(String name) throws Exception {
        String path = name+" raiz";
        return search(root, name, path);
    }
    private Jogador getPlayer(Nodle i, String key) throws Exception {
        if(i == null) {
            return null;
        }else if(keyComparator(key, i) == 0) {
            return i.getElement();
        }
        else if (keyComparator(key, i) < 0) 
        {
            return getPlayer(i.getLeft(), key);
        }
        else if (keyComparator(key, i) > 0)
        {
            return getPlayer(i.getRight(), key);
        }
        else { throw new Exception("A BST cannot have elements with the same key"); }
    }
    public Jogador getPlayer(String playerName) throws Exception{
        return getPlayer(root, playerName);
    }
}

class Q5 {

    public static Jogador sequentialSearchByID(Jogador[] arr, int id){
        for(Jogador player : arr){
            if(player.getId() == id)
                return player;
        }
        return null;
    }
    public static void main(String[] args) throws Exception {
      // ..................................................PART 1..............................................
        //String path = "C:\\Users\\Pichau\\Desktop\\TP04\\anunciado\\players.csv";
        String path = "/tmp/players.csv";

        Jogador[] totalJogadores = new Jogador[3922];
        int index = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            br.readLine();
            while (br.ready()) {
                Jogador tmp = new Jogador();
                tmp.ler(br.readLine());
                totalJogadores[index++] = tmp;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
      // ..................................................PART 2..............................................
        Scanner sc = new Scanner(System.in);

        List<Jogador> selectedPlayers = new ArrayList<>();
        
        String input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                selectedPlayers.add(playerFound);
            }
        }
      // ..................................................PART 3..............................................

        BinarySearchTree treeSort = new BinarySearchTree();

        String nameToSearch = sc.nextLine();
        while (!nameToSearch.equals("FIM")) 
        {
            for(Jogador player : selectedPlayers){
                if(player.getNome().equals(nameToSearch))
                {
                    treeSort.insert(player);
                    break;
                }
            }
            nameToSearch = sc.nextLine();
        }
      // ..................................................PART 4..............................................
        treeSort.inOrder();
        System.out.println(treeSort.comparisons);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("matricula_treesort.txt"))){
            bw.write(String.valueOf(treeSort.comparisons));
        }catch(IOException e){
            e.printStackTrace();
        }

        sc.close();
    }
}