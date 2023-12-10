
import java.io.BufferedReader;
import java.io.FileReader;
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

class No {
    public char elemento;
    public final int tamanho = 255;
    public No[] prox;
    public boolean folha;

    public No() {
        this(' ');
    }

    public No(char elemento) {
        this.elemento = elemento;
        prox = new No[tamanho];
        for (int i = 0; i < tamanho; i++)
            prox[i] = null;
        folha = false;
    }

    public static int hash(char x) {
        return (int) x;
    }
}

class ArvoreTrie {
    private No raiz;

    public ArvoreTrie() {
        raiz = new No();
    }

    public boolean pesquisar(String s) throws Exception {
        return pesquisar(s, raiz, 0);
    }

    public boolean pesquisar(String s, No no, int i) throws Exception {
        boolean resp;
        if (no.prox[s.charAt(i)] == null) {
            resp = false;
        } else if (i == s.length() - 1) {
            resp = (no.prox[s.charAt(i)].folha == true);
        } else if (i < s.length() - 1) {
            resp = pesquisar(s, no.prox[s.charAt(i)], i + 1);
        } else {
            throw new Exception("Erro ao pesquisar!");
        }
        return resp;
    }

    public void inserir(String s) throws Exception {
        inserir(s, raiz, 0);
    }

    private void inserir(String s, No no, int i) throws Exception {

        if (no.prox[s.charAt(i)] == null) {

            no.prox[s.charAt(i)] = new No(s.charAt(i));

            if (i == s.length() - 1) {

                no.prox[s.charAt(i)].folha = true;
            } else {
                inserir(s, no.prox[s.charAt(i)], i + 1);
            }

        } else if (no.prox[s.charAt(i)].folha == false && i < s.length() - 1) {
            inserir(s, no.prox[s.charAt(i)], i + 1);

        } else {
            throw new Exception("Erro ao inserir!");
        }
    }

    public void mostrar() {
        mostrar("", raiz);
    }

    public void mostrar(String s, No no) {
        if (no.folha == true) {
            System.out.println((s + no.elemento));
        } else {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {

                    mostrar(s + no.elemento, no.prox[i]);
                }
            }
        }
    }

    public int contarAs() {
        int resp = 0;
        if (raiz != null) {
            resp = contarAs(raiz);
        }
        return resp;
    }

    public int contarAs(No no) {
        int resp = (no.elemento == 'A') ? 1 : 0;

        if (no.folha == false) {
            for (int i = 0; i < no.prox.length; i++) {
                if (no.prox[i] != null) {
                    resp += contarAs(no.prox[i]);
                }
            }
        }
        return resp;
    }

    public List<String> getAllWords() {
        List<String> res = new ArrayList<>();

        getAllWords(raiz, res, "");

        return res;
    }

    private void getAllWords(No i, List<String> lista, String str) {
        if (i != null) {
            if(i != raiz){
                str += i.elemento;
            }
            if (i.folha) {
                lista.add(str);
            } else {
                for (No ref : i.prox) {
                    getAllWords(ref, lista, str);
                }
            }
        }
    }

    public No getRaiz() {
        return raiz;
    }

    public void setRaiz(No raiz) {
        this.raiz = raiz;
    }
}

public class Q6 {
    public static Jogador sequentialSearchByID(Jogador[] arr, int id) {
        for (Jogador player : arr) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    public static Jogador sequentialSearchByNAME(Jogador[] arr, String name) {
        for (Jogador player : arr) {
            if (player.getNome().equals(name))
                return player;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        // ..................................................PART 1

        // String path = "C:\\Users\\Pichau\\Desktop\\TP04\\anunciado\\players.csv";
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
        // ..................................................PART 2

        Scanner sc = new Scanner(System.in);

        ArvoreTrie trie1 = new ArvoreTrie();

        String input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                trie1.inserir(playerFound.getNome());
                System.out.println(input + "   " + playerFound.getNome());

            }
        }

        // ..................................................PART 3

        ArvoreTrie trie2 = new ArvoreTrie();

        input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                trie2.inserir(playerFound.getNome());
                System.out.println(input + "   " + playerFound.getNome());
            }
        }
        // ..................................................PART 4
        ArvoreTrie trie3 = new ArvoreTrie();

        int tam_1 = trie1.getAllWords().size();
        String[] strings_1 = trie1.getAllWords().toArray(new String[tam_1]);

        int tam_2 = trie2.getAllWords().size();
        String[] strings_2 = trie2.getAllWords().toArray(new String[tam_2]);

        for (String s1 : strings_1) {
            trie3.inserir(s1);
        }
        for (String s2 : strings_2) {
            trie3.inserir(s2);
        }


        // ..................................................PART 5
        
        String nameToSearch = sc.nextLine();
        while (!nameToSearch.equals("FIM")) {
            System.out.print(nameToSearch);
            if (trie3.pesquisar(nameToSearch)) {
                System.out.print(" SIM\n");
            } else {
                System.out.print(" NAO\n");
            }
            nameToSearch = sc.nextLine();
        }

        sc.close();
    }
}
