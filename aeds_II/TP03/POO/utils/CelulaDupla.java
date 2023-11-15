package utils;

public class CelulaDupla {
    private CelulaDupla prox;
    private CelulaDupla ant;
    private Jogador elemento;
    
    public CelulaDupla() {
        this.prox = null;
        this.ant = null;
        this.elemento = null;
    }
    public CelulaDupla(Jogador elemento) {
        this.elemento = elemento;
        this.prox = null;
        this.ant = null;
    }
    public CelulaDupla getProx() {
        return prox;
    }
    public void setProx(CelulaDupla prox) {
        this.prox = prox;
    }
    public CelulaDupla getAnt() {
        return ant;
    }
    public void setAnt(CelulaDupla ant) {
        this.ant = ant;
    }
    public Jogador getElemento() {
        return elemento;
    }
    public void setElemento(Jogador elemento) {
        this.elemento = elemento;
    }
    
}
