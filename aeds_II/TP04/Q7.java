import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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

class HashOverflow {
    final int tamTab = 21;
    final int tamRerva = 9;

    public int indexReserva;
    public Jogador[] tabela;

    public HashOverflow() {
        int tamTotal = tamTab + tamTab;
        this.tabela = new Jogador[tamTotal];

        this.indexReserva = 0;

        for (int i = 0; i < tamTotal; i++) {
            tabela[i] = null;
        }
    }

    private int hash(Jogador x) {
        return x.getAltura() % tamTab;
    }

    public void inserir(Jogador x) throws Exception {
        if (x == null) {
            return;
        }
        int pos = hash(x);
        if (tabela[pos] == null) {
            tabela[pos] = x;
        } else {
            if (indexReserva < tamRerva) {
                tabela[tamTab + indexReserva++] = x;
            }
        }
    }

    public void mostrar() {
        System.out.print("\nTabela: [");
        for (int i = 0; i < tamTab; i++) {
            if (tabela[i] != null) {
                System.out.print(" " + tabela[i].getNome() + " ");
            }
        }
        System.out.print("]\n");
        System.out.print("\nReserva: [");
        for (int i = tamTab; i < tamTab + tamTab; i++) {
            if (tabela[i] != null) {
                System.out.print(" " + tabela[i].getNome() + " ");
            }
        }
        System.out.print("]\n");
    }

    private Boolean pesquisarReserva(Jogador x) {
        int tamTotal = indexReserva + tamTab;
        for (int i = tamTab; i < tamTotal; i++) {
            if (tabela[i] != null && tabela[i].getNome().equals(x.getNome())) {
                return true;
            }
        }
        return false;
    }

    public Boolean pesquisar(Jogador x) {
        int pos = hash(x);
        if (tabela[pos] != null && tabela[pos].getNome().equals(x.getNome())) {
            return true;
        }
        return pesquisarReserva(x);
    }
}

public class Q7 {
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

        HashOverflow tabela = new HashOverflow();

        String input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                tabela.inserir(playerFound);
            }
        }

        // ..................................................PART 3

        String nameToSearch = sc.nextLine();
        while (!nameToSearch.equals("FIM")) {

            Jogador playerFound = sequentialSearchByNAME(totalJogadores, nameToSearch);

            System.out.print(nameToSearch);

            if (playerFound != null) {
                if (tabela.pesquisar(playerFound)) {
                    System.out.print(" SIM\n");
                } else {
                    System.out.print(" NAO\n");
                }
            }
            nameToSearch = sc.nextLine();
        }

        sc.close();
    }
}
