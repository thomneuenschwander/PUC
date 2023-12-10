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
    private int anoNascimento;
    private String cidadeNascimento;
    private String estadoNascimento;

    public Jogador() {
        id = 0;
        nome = "";
        altura = 0;
        peso = 0;
        universidade = "";
        anoNascimento = 0;
        cidadeNascimento = "";
        estadoNascimento = "";
    }

    public Jogador(int id, String nome, int altura, int peso, String universidade, int anoNascimento,
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

    public int getAnoNascimento() {
        return anoNascimento;
    }

    public void setAnoNascimento(int anoNascimento) {
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
        setAnoNascimento(Integer.parseInt(data[5]));
        setEstadoNascimento(data[6]);
        setCidadeNascimento(data[7]);
    }

    public void imprimir() {
        System.out.println("[" + getId() + " ## " + getNome() + " ## " + getAltura() + " ## " + getPeso() + " ## "
                + getAnoNascimento() + " ## " + getUniversidade() + " ## " + getEstadoNascimento() + " ## "
                + getCidadeNascimento() + "]");
    }

    public Jogador clone() {
        Jogador clone = new Jogador();
        clone.id = this.id;
        clone.nome = this.nome;
        clone.altura = this.altura;
        clone.peso = this.peso;
        clone.universidade = this.universidade;
        clone.anoNascimento = this.anoNascimento;
        clone.cidadeNascimento = this.cidadeNascimento;
        clone.estadoNascimento = this.estadoNascimento;
        return clone;
    }
}

class Cmp {
    int count;

    public Cmp(int count) {
        this.count = count;
    }

    public void contar() {
        count++;
    }

    public int getCount() {
        return count;
    }

}

class Mov {
    int count;

    public Mov(int count) {
        this.count = count;
    }

    public void contar() {
        count++;
    }

    public int getCount() {
        return count;
    }

}

public class Q09 {
    public static Jogador[] changeArraySize (Jogador jogador[], int n){
        
        Jogador resp[] = new Jogador[n];

        for (int i = 0; i < n; i++) {
            resp[i] = jogador[i].clone();
        }

        return resp;
    }

    public static int mov = 0;
    public static int comp = 0;

    public static void swap(Jogador[] jogador, int i, int j) {
        Jogador aux = jogador[i];
        jogador[i] = jogador[j];
        jogador[j] = aux;
        
    }

    public static Jogador buscarPorId(List<Jogador> lista, int id) {
        for (Jogador j : lista) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    public static int n;

    public static void heapsort(Jogador[] arr, int tamanho) {
        construir(arr, tamanho);
        for (int i = tamanho - 1; i > 0; i--) {
            
            swap(arr, 0, i);
            reconstruir(arr, 0, i);
        }
    }

    public static void construir(Jogador[] arr, int tamanho) {
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            
            reconstruir(arr, i, tamanho);
        }
    }

    public static void reconstruir(Jogador[] arr, int i, int tamanho) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        if (esq < tamanho && (arr[esq].getAltura() > arr[maior].getAltura() ||
                (arr[esq].getAltura() == arr[maior].getAltura() &&
                        arr[esq].getNome().compareTo(arr[maior].getNome()) > 0))) {
            
            maior = esq;
        }
        if (dir < tamanho && (arr[dir].getAltura() > arr[maior].getAltura() ||
                (arr[dir].getAltura() == arr[maior].getAltura() &&
                        arr[dir].getNome().compareTo(arr[maior].getNome()) > 0))) {
            
            maior = dir;
        }
        if (maior != i) {
            
            swap(arr, i, maior);
            reconstruir(arr, maior, tamanho);
        }
    }

    public static void main(String[] args) {

        //String path = "players.csv";
        String path = "/tmp/players.csv";

        List<Jogador> listaJogador = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {

            br.readLine();
            while (br.ready()) {
                // System.out.println(br.readLine());
                Jogador j = new Jogador();
                j.ler(br.readLine());
                listaJogador.add(j);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scanner sc = new Scanner(System.in);
        String entrada = "entrada";
        int tam = 3923;
        Jogador[] vetJogadores = new Jogador[tam];
        int j = 0;
        while (!entrada.equals("FIM")) {
            entrada = sc.nextLine();
            if (!entrada.equals("FIM")) {
                int id = Integer.parseInt(entrada);
                Jogador jogadorEncontrado = buscarPorId(listaJogador, id);
                if (jogadorEncontrado != null) {
                    vetJogadores[j] = jogadorEncontrado;
                    j++;
                }
            }
        }
        vetJogadores = changeArraySize(vetJogadores, j);
        
        heapsort(vetJogadores, j);

        // inicio da contagem de comparacoes
        Cmp comp = new Cmp(0);
        // inicio da contagem de mov
        Mov mov = new Mov(0);

        // inicio do cronometro
        long startTime = System.currentTimeMillis();

        long time = System.currentTimeMillis() - startTime;
        // imprimir o nome dos jogadores
        for (int i = 0; i < j; i++) {
            vetJogadores[i].imprimir();
        }
        // escrever no arquivo log
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("matricula_heapsort.txt"))) {

            String matricula = "802717";

            bw.write(matricula + "\t" + comp + "\t" + mov + "\t" + time);

        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}