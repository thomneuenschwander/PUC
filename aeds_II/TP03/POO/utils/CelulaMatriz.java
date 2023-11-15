package utils;

public class CelulaMatriz {
    private CelulaMatriz esq, dir, topo, baixo;
    private int elemento;

    public CelulaMatriz(){
        esq = dir = topo = baixo = null;
        this.elemento = 0;
    }
    public CelulaMatriz(int x){
        this.elemento = x;
        this.dir = null;
        this.esq = null;
        this.baixo = null;
        this.topo = null;
    }

    public CelulaMatriz getEsq() {
        return esq;
    }

    public void setEsq(CelulaMatriz esq) {
        this.esq = esq;
    }

    public CelulaMatriz getDir() {
        return dir;
    }

    public void setDir(CelulaMatriz dir) {
        this.dir = dir;
    }

    public CelulaMatriz getTopo() {
        return topo;
    }

    public void setTopo(CelulaMatriz topo) {
        this.topo = topo;
    }

    public CelulaMatriz getBaixo() {
        return baixo;
    }

    public void setBaixo(CelulaMatriz baixo) {
        this.baixo = baixo;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }
    
}
