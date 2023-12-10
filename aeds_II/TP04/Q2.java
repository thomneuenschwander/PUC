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

class Nodle2 {
    private String playerName;
    private Nodle2 left, right;

    public Nodle2(String playerName) {
        this.playerName = playerName;
        this.left = this.right = null;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Nodle2 getLeft() {
        return left;
    }

    public void setLeft(Nodle2 left) {
        this.left = left;
    }

    public Nodle2 getRight() {
        return right;
    }

    public void setRight(Nodle2 right) {
        this.right = right;
    }

    public static int comparisonKey(Jogador player, Nodle2 nodle) {
        return player.getNome().compareTo(nodle.getPlayerName());
    }

    public static int comparisonKey(String playerName, Nodle2 nodle) {
        return playerName.compareTo(nodle.getPlayerName());
    }
}

class Nodle1 {
    private int mod;
    private Nodle1 left, right;
    private Nodle2 root;

    public Nodle1(int mod) {
        this(mod, null, null, null);
    }

    public Nodle1(int mod, Nodle1 left, Nodle1 right, Nodle2 root) {
        this.mod = mod;
        this.left = left;
        this.right = right;
        this.root = root;
    }

    public int getMod() {
        return mod;
    }

    public void setMod(int mod) {
        this.mod = mod;
    }

    public Nodle1 getLeft() {
        return left;
    }

    public void setLeft(Nodle1 left) {
        this.left = left;
    }

    public Nodle1 getRight() {
        return right;
    }

    public void setRight(Nodle1 right) {
        this.right = right;
    }

    public Nodle2 getRoot() {
        return root;
    }

    public void setRoot(Nodle2 root) {
        this.root = root;
    }

    public static int comparisonKey(String playerName, Nodle2 nodle) {
        return playerName.compareTo(nodle.getPlayerName());
    }

    public boolean search(String name, Nodle2 j) {
        if (j == null) {
            return false;
        } else if (comparisonKey(name, j) == 0) {
            return true;
        } else if (comparisonKey(name, j) < 0) {
            return search(name, j.getLeft());
        } else {
            return search(name, j.getRight());
        }
    }

    public static Boolean show(Jogador x, Nodle2 j, Boolean resp) {
        if (j != null && resp == false) {
            System.out.print(" ESQ");
            show(x, j.getLeft(), resp);

            if (comparisonKey(x.getNome(), j) == 0) {
                resp = true;
            }

            if (resp == false) {
                System.out.print(" DIR");
                show(x, j.getRight(), resp);
            }
        }
        return resp;
    }
}

class TreeOfTree {
    private Nodle1 root;
    private int key;

    public TreeOfTree() {
        this.key = 0;
        this.root = null;
    }

    public TreeOfTree(int[] arr, int key) {
        this.key = key;
        this.root = null;
        for (int mod : arr) {
            insert(mod);
        }
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    // ----------------------------------------------------INSERT-------------------------------------
    private void insert(int mod) {
        root = insert(mod, root);
    }

    private Nodle1 insert(int mod, Nodle1 i) {
        if (i == null) {
            i = new Nodle1(mod);
        } else if (mod < i.getMod()) {
            i.setLeft(insert(mod, i.getLeft()));
        } else if (mod > i.getMod()) {
            i.setRight(insert(mod, i.getRight()));
        }
        return i;
    }

    public void insert(Jogador player) {
        root = insert(player, root);
    }

    private Nodle1 insert(Jogador player, Nodle1 i) {
        if (MOD(player) == i.getMod()) {
            i.setRoot(insert(player, i.getRoot()));
        } else if (MOD(player) < i.getMod()) {
            i.setLeft(insert(player, i.getLeft()));
        } else if (MOD(player) > i.getMod()) {
            i.setRight(insert(player, i.getRight()));
        }
        return i;
    }

    private Nodle2 insert(Jogador player, Nodle2 i) {
        if (i == null) {
            i = new Nodle2(player.getNome());
        } else if (Nodle2.comparisonKey(player, i) < 0) {
            i.setLeft(insert(player, i.getLeft()));
        } else if (Nodle2.comparisonKey(player, i) > 0) {
            i.setRight(insert(player, i.getRight()));
        }
        return i;
    }

    // --------------------------------------------------------------------------------------------------
    private int MOD(Jogador player) {
        return player.getAltura() % key;
    }

    // -----------------------------------------------------------------------
    // TRAVERSAL ------------------------------------------
    public void halfInOrder() {
        System.out.print("Half in order traversall -> [ ");
        halfInOrder(root);
        System.out.print("]");
        System.out.println();
    }

    private void halfInOrder(Nodle1 i) {
        if (i != null) {
            halfInOrder(i.getLeft());
            System.out.print(i.getMod() + " ");
            halfInOrder(i.getRight());
        }
    }

    public void fullInOrder() {
        System.out.print("Full in order traversall -> {\n");
        fullInOrder(root);
        System.out.print("}");
        System.out.println();
    }

    private void fullInOrder(Nodle1 i) {
        if (i != null) {
            fullInOrder(i.getLeft());
            System.out.print("[ | ");
            fullInOrder(i.getRoot());
            System.out.print("]\n");
            fullInOrder(i.getRight());
        }
    }

    private void fullInOrder(Nodle2 i) {
        if (i != null) {
            fullInOrder(i.getLeft());
            System.out.print(i.getPlayerName() + " | ");
            fullInOrder(i.getRight());
        }
    }
    // -----------------------------------------------------------------------------------------------------------------------------------

    public Boolean show(Jogador x) {
        Boolean resp = false;
        return show(x, root, resp);
    }

    private Boolean show(Jogador x, Nodle1 i, Boolean resp) {
        if (i != null && resp == false) {
            System.out.print(" esq");
            show(x, i.getLeft(), resp);

            resp = Nodle1.show(x, i.getRoot(), resp);

            System.out.print(" dir");
            show(x, i.getRight(), resp);
        }
        return resp;
    }
    // ----------------------------------------------- SEARCH
    // ------------------------------------------------------------------------------------

    public boolean mostrarPre(String name) {
    return mostrarPre(name, root, false);
}

private boolean mostrarPre(String name, Nodle1 i, boolean resp) {
    if (i != null && !resp) {
        resp = mostrarPre(name, i.getRoot(), resp);
        if (resp) {

            return true;
        } else {
            System.out.print(" esq");
            resp = mostrarPre(name, i.getLeft(), resp);

            if (!resp) {  // Se ainda não encontrou, explore o lado direito
                System.out.print(" dir");
                resp = mostrarPre(name, i.getRight(), resp);
            }
        }
    }
    return resp;
}

public boolean mostrarPre(String name, Nodle2 j, boolean resp) {
    if (j != null && !resp) {
        resp = name.equals(j.getPlayerName());

        if (resp) {

            return true;
        } else {
            System.out.print(" ESQ");
            resp = mostrarPre(name, j.getLeft(), resp);

            if (!resp) {  
                System.out.print(" DIR");
                resp = mostrarPre(name, j.getRight(), resp);
            }
        }
    }
    return resp;
}

}

class Q2 {
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

        // ..................................................PART 2

        Scanner sc = new Scanner(System.in);

        int key = 15;
        int[] treeOrder = { 7, 3, 11, 1, 5, 9, 12, 0, 2, 4, 6, 8, 10, 13, 14 };

        TreeOfTree treeOftree = new TreeOfTree(treeOrder, key);

        String input = "";
        while (!input.equals("FIM")) {
            input = sc.nextLine();
            if (!input.equals("FIM")) {
                int id = Integer.parseInt(input);
                Jogador playerFound = sequentialSearchByID(totalJogadores, id);
                treeOftree.insert(playerFound);
            }
        }
        // ................................................. PART 3

        String nameToSearch = sc.nextLine();
        while (!nameToSearch.equals("FIM")) {
            Jogador playerFound = sequentialSearchByNAME(totalJogadores, nameToSearch);

            if (playerFound != null)
            {
                System.out.print(playerFound.getNome()+" raiz");
                if (treeOftree.mostrarPre(playerFound.getNome())) {
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