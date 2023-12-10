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

class Comp {
    int count;

    public Comp(int x) {
        count = x;
    }

    public void contar() {
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class Q03 {

    public static String pesquisaSequencial(List<Jogador> lista, String nome, Comp count) {
        for (Jogador j : lista) {
            count.contar();
            if (j.getNome().equals(nome)) {
                count.contar();
                return "SIM";
            }
        }
        return "NAO";
    }

    public static Jogador buscarPorId(List<Jogador> lista, int id, Comp count) {
        for (Jogador j : lista) {
            count.contar();
            if (j.getId() == id) {
                count.contar();
                return j;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // inicio da contagem de comparacoes
        Comp count = new Comp(0);

        // inicio do cronometro
        long startTime = System.currentTimeMillis();

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
        List<Jogador> listaExclusives = new ArrayList<>();
        while (!entrada.equals("FIM")) {
            count.contar();
            entrada = sc.nextLine();
            if (!entrada.equals("FIM")) {
                count.contar();
                int id = Integer.parseInt(entrada);
                Jogador jogadorEncontrado = buscarPorId(listaJogador, id, count);
                listaExclusives.add(jogadorEncontrado);
            }
        }
        entrada = "segunda entrada";
        while (!entrada.equals("FIM")) {
            count.contar();
            entrada = sc.nextLine();
            if (!entrada.equals("FIM")) {
                count.contar();
                System.out.println(pesquisaSequencial(listaExclusives, entrada, count));
            }
        }

        long time = System.currentTimeMillis() - startTime;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("matricula_sequencial.txt"))) {

            String matricula = "802717";

            int comparacoes = count.getCount();

            bw.write(matricula + "\t" + time + "\t" + comparacoes);

        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }
}