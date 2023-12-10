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

public class Q13 {
    public static int k = 10;

    public static Jogador buscarPorId(List<Jogador> lista, int id) {
        for (Jogador j : lista) {
            if (j.getId() == id) {
                return j;
            }
        }
        return null;
    }

    public static void insertionSortParcial(Jogador[] arr, int n) {
        for (int i = 1; i < n; i++) {
            Jogador tmp = arr[i];

            int j = (i < k) ? i - 1 : k - 1;
            int n1 = Integer.parseInt(arr[j].getAnoNascimento());
            int n2 = Integer.parseInt(tmp.getAnoNascimento());
            while ((j >= 0) && (n1 > n2)) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = tmp;
        }
    }

    public static void insertionSort(Jogador[] arr, int n) {

        for (int i = 1; i < n; i++) {

            Jogador tmp = arr[i];
            int j = i - 1;

            while ((j >= 0)
                    && (Integer.parseInt(arr[j].getAnoNascimento()) > Integer.parseInt(tmp.getAnoNascimento()))) {
                arr[j + 1] = arr[j];
                j--;
            }

            while ((j >= 0) && (Integer.parseInt(arr[j].getAnoNascimento()) == Integer.parseInt(tmp.getAnoNascimento())
                    && arr[j].getNome().compareTo(tmp.getNome()) > 0)) {
                arr[j + 1] = arr[j];
                j--;
            }

            arr[j + 1] = tmp;
        }

    }

    public static void intercalar(int esq, int meio, int dir, Jogador[] arr) {

        int nEsq = (meio + 1) - esq;
        int nDir = dir - meio;
        Jogador[] arrayEsq = new Jogador[nEsq + 1];
        Jogador[] arrayDir = new Jogador[nDir + 1];

        Jogador z1 = new Jogador();
        Jogador z2 = new Jogador();
        z2.setUniversidade("zzzzzzzzzzzzzzzz");
        z1.setUniversidade("zzzzzzzzzzzzzzzz");
        arrayEsq[nEsq] = z1;
        arrayDir[nDir] = z2;
        int iEsq, iDir, i;

        for (iEsq = 0; iEsq < nEsq; iEsq++) {
            arrayEsq[iEsq] = arr[esq + iEsq];
        }

        for (iDir = 0; iDir < nDir; iDir++) {
            arrayDir[iDir] = arr[(meio + 1) + iDir];
        }

        for (iEsq = iDir = 0, i = esq; i <= dir; i++) {
            int comparacaoUniversidade = arrayEsq[iEsq].getUniversidade().compareTo(arrayDir[iDir].getUniversidade());
            
            if (comparacaoUniversidade == 0) {

                comparacaoUniversidade = arrayEsq[iEsq].getNome().compareTo(arrayDir[iDir].getNome());
            }
            
            arr[i] = (comparacaoUniversidade <= 0) ? arrayEsq[iEsq++] : arrayDir[iDir++];
        }

    }

    public static void mergeSort(int esq, int dir, Jogador[] arr) {
        if (esq < dir) {
            int meio = (esq + dir) / 2;
            mergeSort(esq, meio, arr);
            mergeSort(meio + 1, dir, arr);
            intercalar(esq, meio, dir, arr);
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
        mergeSort(0, j-1, vetJogadores);
        for (int i = 0; i < j; i++) {
            vetJogadores[i].imprimir();
        }
        sc.close();
    }

}