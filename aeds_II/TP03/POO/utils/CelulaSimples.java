package utils;

public class CelulaSimples {

    private CelulaSimples prox;
    private Jogador elemento;
    
    public CelulaSimples(Jogador x) {
        prox = null;
        elemento = x;
    }
    public CelulaSimples getProx() {
        return prox;
    }
    public void setProx(CelulaSimples prox) {
        this.prox = prox;
    }
    public Jogador getElemento() {
        return elemento;
    }
    public void setElemento(Jogador elemento) {
        this.elemento = elemento;
    }
}