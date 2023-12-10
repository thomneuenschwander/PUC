
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
        jogadorClone.cidadeNascimento = this.cidadeNascimento;
        jogadorClone.estadoNascimento = this.estadoNascimento;
        return jogadorClone;
    }
}

public class Q11 {
    public static int k = 10;

    public static Jogador buscarPorId(List<Jogador> lista, int id) {
        for (Jogador j : lista) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    public static void parcialSelectionSort(Jogador[] arr, int n) {
        for (int i = 0; i < k; i++) {
            int menor = i;
            for (int j = (i + 1); j < n; j++) {
                int comparacao = arr[menor].getNome().compareTo(arr[j].getNome());
                if (comparacao > 0) {
                    menor = j;
                }
            }
            Jogador temp = arr[menor];
            arr[menor] = arr[i];
            arr[i] = temp;
        }
    }

    public static Jogador[] changeArrSize(Jogador[] arr, int j) {
        Jogador novo[] = new Jogador[j];
        for (int i = 0; i < j; i++) {
            novo[i] = arr[i].clone();
        }
        return novo;
    }

    // --------------------------------------------------------------------------------------------------------------------------
    public static void insertionSort(Jogador[] arr, int n) {
        for (int i = 1; i < n; i++) {
            Jogador temp = arr[i];
            int j = i - 1;
            while (j >= 0
                    && (arr[j].getAltura() == temp.getAltura() && arr[j].getNome().compareTo(temp.getNome()) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = temp;
        }

    }

    public static int getMaiorAltura(Jogador[] arr, int n) {
        int maior = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i].getAltura() > maior) {
                maior = arr[i].getAltura();
            }
        }
        return maior;
    }

    public static void countingSort(Jogador[] jogador, int n) {
        int[] count = new int[getMaiorAltura(jogador, n) + 1];
        Jogador[] ordenado = new Jogador[n];

        for (int i = 0; i < count.length; count[i] = 0, i++)
            ;

        for (int i = 0; i < n; count[jogador[i].getAltura()]++, i++)
            ;

        for (int i = 1; i < count.length; count[i] += count[i - 1], i++)
            ;

        for (int i = n - 1; i >= 0; i--) {
            ordenado[count[jogador[i].getAltura()] - 1] = jogador[i].clone();
            count[jogador[i].getAltura()]--;
        }

        for (int i = 0; i < n; i++) {
            jogador[i] = ordenado[i].clone();
        }

        insertionSort(jogador, n);

    }

    // --------------------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        // String path = "players.csv";
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
        int tam = 3293;
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
        vetJogadores = changeArrSize(vetJogadores, j);
        countingSort(vetJogadores, j);

        for (int i = 0; i < j; i++) {
            vetJogadores[i].imprimir();
        }
        // long time = System.currentTimeMillis() - startTime;
        // try (BufferedWriter bw = new BufferedWriter(new
        // FileWriter("matricula_countingSort.txt"))) {

        // String matricula = "802717";

        // int comparacoes = count.getCount();

        // bw.write(matricula + "\t" + time + "\t" + comparacoes);

        // } catch (IOException e) {
        // e.printStackTrace();
        // }
        sc.close();
    }
}