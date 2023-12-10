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

class NoRB {
    public boolean cor;
    public Jogador elemento;
    public NoRB esq, dir;

    public NoRB() {
        this(null);
    }

    public NoRB(Jogador elemento) {
        this(elemento, false, null, null);
    }

    public NoRB(Jogador elemento, boolean cor) {
        this(elemento, cor, null, null);
    }

    public NoRB(Jogador elemento, boolean cor, NoRB esq, NoRB dir) {
        this.cor = cor;
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
    }
}

class RedBlack {
    private NoRB raiz;

    public RedBlack() {
        raiz = null;
    }

    private int key(String playerName, NoRB b) {
        return playerName.compareTo(b.elemento.getNome());
    }

    private int key(NoRB a, NoRB b) {
        return a.elemento.getNome().compareTo(b.elemento.getNome());
    }

    private int key(Jogador x, NoRB b) {
        return x.getNome().compareTo(b.elemento.getNome());
    }

    public boolean pesquisar(Jogador elemento) {
        return pesquisar(elemento, raiz);
    }

    private boolean pesquisar(Jogador x, NoRB i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (key(x, i) == 0) {
            resp = true;
        } else if (key(x, i) < 0) {
            resp = pesquisar(x, i.esq);
        } else {
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public boolean pesquisar(String playerName) {
        return pesquisar(playerName, raiz);
    }

    private boolean pesquisar(String x, NoRB i) {
        boolean resp;
        if (i == null) {
            resp = false;
        } else if (key(x, i) == 0) {
            resp = true;
        } else if (key(x, i) < 0) {
            System.out.print(" esq");
            resp = pesquisar(x, i.esq);
        } else {
            System.out.print(" dir");
            resp = pesquisar(x, i.dir);
        }
        return resp;
    }

    public void caminharCentral() {
        System.out.print("[ ");
        caminharCentral(raiz);
        System.out.println("]");
    }

    private void caminharCentral(NoRB i) {
        if (i != null) {
            caminharCentral(i.esq);
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) "));
            caminharCentral(i.dir);
        }
    }

    public void caminharPre() {
        System.out.print("[ ");
        caminharPre(raiz);
        System.out.println("]");
    }

    private void caminharPre(NoRB i) {
        if (i != null) {
            System.out.print(i.elemento.getNome() + ((i.cor) ? "(p) " : "(b) "));
            caminharPre(i.esq);
            caminharPre(i.dir);
        }
    }

    public void caminharPos() {
        System.out.print("[ ");
        caminharPos(raiz);
        System.out.println("]");
    }

    private void caminharPos(NoRB i) {
        if (i != null) {
            caminharPos(i.esq);
            caminharPos(i.dir);
            System.out.print(i.elemento + ((i.cor) ? "(p) " : "(b) "));
        }
    }

    public void inserir(Jogador x) throws Exception {

        if (raiz == null) {
            raiz = new NoRB(x);

        } else if (raiz.esq == null && raiz.dir == null) {
            if (key(x, raiz) < 0) {
                raiz.esq = new NoRB(x);
            } else {
                raiz.dir = new NoRB(x);
            }

        } else if (raiz.esq == null) {
            if (key(x, raiz) < 0) {
                raiz.esq = new NoRB(x);

            } else if (key(x, raiz.dir) < 0) {
                raiz.esq = new NoRB(raiz.elemento);
                raiz.elemento = x;

            } else {
                raiz.esq = new NoRB(raiz.elemento);
                raiz.elemento = raiz.dir.elemento;
                raiz.dir.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } else if (raiz.dir == null) {
            if (key(x, raiz) > 0) {
                raiz.dir = new NoRB(x);
            } else if (key(x, raiz.esq) > 0) {
                raiz.dir = new NoRB(raiz.elemento);
                raiz.elemento = x;
            } else {
                raiz.dir = new NoRB(raiz.elemento);
                raiz.elemento = raiz.esq.elemento;
                raiz.esq.elemento = x;
            }
            raiz.esq.cor = raiz.dir.cor = false;

        } else {

            inserir(x, null, null, null, raiz);
        }
        raiz.cor = false;
    }

    private void balancear(NoRB bisavo, NoRB avo, NoRB pai, NoRB i) {

        if (pai.cor == true) {

            if (key(pai, avo) > 0) {
                if (key(i, pai) > 0) {
                    avo = rotacaoEsq(avo);
                } else {
                    avo = rotacaoDirEsq(avo);
                }
            } else {
                if (key(i, pai) < 0) {
                    avo = rotacaoDir(avo);
                } else {
                    avo = rotacaoEsqDir(avo);
                }
            }
            if (bisavo == null) {
                raiz = avo;
            } else if (key(avo, bisavo) < 0) {
                bisavo.esq = avo;
            } else {
                bisavo.dir = avo;
            }

            avo.cor = false;
            avo.esq.cor = avo.dir.cor = true;

        }
    }

    private void inserir(Jogador x, NoRB bisavo, NoRB avo, NoRB pai, NoRB i) throws Exception {
        if (i == null) {
            if (key(x, pai) < 0) {
                i = pai.esq = new NoRB(x, true);
            } else {
                i = pai.dir = new NoRB(x, true);
            }
            if (pai.cor == true) {
                balancear(bisavo, avo, pai, i);
            }
        } else {
            if (i.esq != null && i.dir != null && i.esq.cor == true && i.dir.cor == true) {
                i.cor = true;
                i.esq.cor = i.dir.cor = false;
                if (i == raiz) {
                    i.cor = false;
                } else if (pai.cor == true) {
                    balancear(bisavo, avo, pai, i);
                }
            }
            if (key(x, i) < 0) {
                inserir(x, avo, pai, i, i.esq);
            } else if (key(x, i) > 0) {
                inserir(x, avo, pai, i, i.dir);
            } else {
                throw new Exception("Erro inserir (elemento repetido)!");
            }
        }
    }

    private NoRB rotacaoDir(NoRB no) {

        NoRB noEsq = no.esq;
        NoRB noEsqDir = noEsq.dir;

        noEsq.dir = no;
        no.esq = noEsqDir;

        return noEsq;
    }

    private NoRB rotacaoEsq(NoRB no) {

        NoRB noDir = no.dir;
        NoRB noDirEsq = noDir.esq;

        noDir.esq = no;
        no.dir = noDirEsq;
        return noDir;
    }

    private NoRB rotacaoDirEsq(NoRB no) {
        no.dir = rotacaoDir(no.dir);
        return rotacaoEsq(no);
    }

    private NoRB rotacaoEsqDir(NoRB no) {
        no.esq = rotacaoEsq(no.esq);
        return rotacaoDir(no);
    }
}

class Q4 {
    public static Jogador sequentialSearchByID(Jogador[] arr, int id) {
        for (Jogador player : arr) {
            if (player.getId() == id)
                return player;
        }
        return null;
    }

    public static void main(String[] args) throws Exception {
        // ..................................................PART
        // 1..............................................
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
        // ..................................................PART
        // 2..............................................
        Scanner sc = new Scanner(System.in);

        RedBlack rb = new RedBlack();

        String input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                rb.inserir(playerFound);
            }
        }
        // ..................................................PART
        // 3..............................................
        String nameToSearch = sc.nextLine();
        while (!nameToSearch.equals("FIM")) {

            System.out.print(nameToSearch + " raiz");
            if (rb.pesquisar(nameToSearch)) {
                System.out.print(" SIM\n");
            } else {
                System.out.print(" NAO\n");
            }
            nameToSearch = sc.nextLine();
        }

        sc.close();
    }
}