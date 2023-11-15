import java.util.Scanner;
import utils.CelulaMatriz;
//----------------------------------------------------------------- MATRIZ FLEXIVEL ----------------------------------------------------

class MatrizFlex {
    private int linhas, colunas;
    private CelulaMatriz inicio;

    public int getLinhas() {
        return linhas;
    }
    public void setLinhas(int linhas) {
        this.linhas = linhas;
    }
    public int getColunas() {
        return colunas;
    }
    public void setColunas(int colunas) {
        this.colunas = colunas;
    }
    public CelulaMatriz getInicio() {
        return inicio;
    }
    public void setInicio(CelulaMatriz inicio) {
        this.inicio = inicio;
    }

    public MatrizFlex(int linhas, int colunas){

        this.linhas = linhas;
        this.colunas = colunas;

        inicio = new CelulaMatriz();
        CelulaMatriz current = inicio;

      // CRIA A PRIMEIRA LINHA
        for(int j = 1; j < colunas; j++){
            current.setDir(new CelulaMatriz());
            current.getDir().setEsq(current);
            current = current.getDir();
        }
        CelulaMatriz esqCel = inicio;

        for(int i = 1; i < linhas; i++){
            CelulaMatriz nova = new CelulaMatriz();
            esqCel.setBaixo(nova);
            nova.setTopo(esqCel);
            current = nova;

            for(int j = 1; j < colunas; j++){
                CelulaMatriz celula = new CelulaMatriz();
                current.setDir(celula);
                celula.setEsq(current);
                celula.setTopo(esqCel.getDir());
                esqCel.getDir().setBaixo(celula);
                esqCel = esqCel.getDir();
                current = current.getDir();
            }
            esqCel = nova;
        }
    }
    public void inserirFlex(int[][] matriz){
        int lin = 0;
        CelulaMatriz i = inicio;
        while(i != null)
        {
            CelulaMatriz j = i;
            int col = 0;
            while (j != null) {
                j.setElemento(matriz[lin][col++]);
                j = j.getDir();
            }
            lin++;
            i = i.getBaixo();
        }
    }
  //----------------------------------------- MOSTRAR FLEXIVEL----------------------------------------------------------------------------
    public void mostrarFlex(){
        CelulaMatriz i = inicio;
        while(i != null)
        {
            CelulaMatriz j = i;
            while (j != null) {
                System.out.print(j.getElemento()+" ");
                j = j.getDir();
            }
            System.out.println();
            i = i.getBaixo();
        }
    }
  //----------------------------------------- MOSTRAR ZIG ZAG ----------------------------------------------------------------------------
    public void mostrarZigZagVertical() {
        CelulaMatriz i = inicio.getDir().getDir();
        boolean descendo = true;
        while(i != null) {
            System.out.println(i.getElemento());
            if(descendo) {
                if(i.getBaixo() != null) {
                    i = i.getBaixo();
                }else{
                    i = i.getDir();
                    descendo = false;
                }
            }else {
                if(i.getTopo() != null) {
                    i = i.getTopo();
                }else{
                    i = i.getDir();
                    descendo = true;
                }
            }
        }
    }
  //----------------------------------------- MOSTRAR DIAGONAL PRINCIPAL ----------------------------------------------------------------------------
    public void mostrarDiagonalPrincipal(){
        CelulaMatriz i = inicio;
        while(i != null) {
            System.out.print(i.getElemento()+" ");
            i = i.getDir();
            if(i != null){
                i = i.getBaixo();
            }
        }
        System.out.println();
    }
  //----------------------------------------- MOSTRAR DIAGONAL SECUNDÁRIA ----------------------------------------------------------------------------
    public void mostrarDiagonalSecundaria(){
        CelulaMatriz i = inicio;
        for(; i.getDir() != null; i = i.getDir());
        while(i != null){
            System.out.print(i.getElemento()+" ");
            i = i.getEsq();
            if(i != null){
                i = i.getBaixo();
            }
        }
        System.out.println();
    }
//--------------------------------------------------------- SOMA ---------------------------------------------------------------------------------------
    public MatrizFlex soma(MatrizFlex matriz){
        MatrizFlex soma = new MatrizFlex(linhas, colunas);

        CelulaMatriz i = soma.inicio, i_m1 = inicio, i_m2 = matriz.inicio;
        
        while(i != null)
        {
            CelulaMatriz j = i, j_m1 = i_m1, j_m2 = i_m2;

            while (j != null) 
            {
                j.setElemento(j_m1.getElemento()+j_m2.getElemento());

                j = j.getDir();
                j_m1 = j_m1.getDir();
                j_m2 = j_m2.getDir();
            }

            i_m1 = i_m1.getBaixo();
            i_m2 = i_m2.getBaixo();
            i = i.getBaixo();
        }

        return soma;
    }
  //--------------------------------------------------------- MULTIPLICACAO ---------------------------------------------------------------------------------------
    
    public MatrizFlex multiplicacao(MatrizFlex matriz){
        MatrizFlex mult = new MatrizFlex(linhas, colunas);

        CelulaMatriz i = mult.inicio;

        CelulaMatriz index_m1 = inicio;
        
        while(i != null)
        {
            CelulaMatriz j = i;

            CelulaMatriz index_m2 = matriz.inicio;
            while (j != null) 
            {
                CelulaMatriz l = index_m1;
                CelulaMatriz coluna_m2 = index_m2;

                int soma = 0;
                while (l != null) 
                {
                    soma += l.getElemento()*coluna_m2.getElemento();
                    l = l.getDir();
                    coluna_m2 = coluna_m2.getBaixo();
                }
                index_m2 = index_m2.getDir();
                j.setElemento(soma);
                j = j.getDir();
            }

            index_m1 = index_m1.getBaixo();
            i = i.getBaixo();
        }

        return mult;
    }
}

public class Q9 {
    public static void preencherMatriz(Scanner sc, int[][] matriz, int linhas, int colunas){
        for(int i = 0; i < linhas; i++){
            for(int j = 0; j < colunas; j++){
                matriz[i][j] = sc.nextInt();
            }
        }
    }
    public static void mostrarMatriz(int[][] matriz, int linhas, int colunas){
        for(int i = 0; i < linhas; i++){
            for(int j = 0; j < colunas; j++){
                System.out.print(matriz[i][j]+" ");
            }
            System.out.println();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        for(int cases = 0; cases < n; cases ++)
        {
          // ----------- MATRIZ 1 -----------------
            int linhas = sc.nextInt();
            int colunas = sc.nextInt();

            int[][] naoFlex1 = new int[linhas][colunas];
            preencherMatriz(sc, naoFlex1, linhas, colunas);

            MatrizFlex m1 = new MatrizFlex(linhas, colunas);
            m1.inserirFlex(naoFlex1);

          // ----------- MATRIZ 2 ------------------
            linhas = sc.nextInt();
            colunas = sc.nextInt();
            int[][] naoFlex2 = new int[linhas][colunas];
            preencherMatriz(sc, naoFlex2, linhas, colunas);
            
            MatrizFlex m2 = new MatrizFlex(linhas, colunas);
            m2.inserirFlex(naoFlex2);

            m1.mostrarDiagonalPrincipal();
            m1.mostrarDiagonalSecundaria();
            MatrizFlex soma = m1.soma(m2);
            MatrizFlex multiplicacao = m1.multiplicacao(m2);

            soma.mostrarFlex();
            multiplicacao.mostrarFlex();

        }




        sc.close();
    }
}
