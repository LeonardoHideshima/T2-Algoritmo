public class No {
    private Documento info;
    private No prox;
    public No(Documento info) {
        this.info = info;
        prox=null;
    }
    public Documento getInfo() {
        return info;
    }
    public void setInfo(Documento info) {
        this.info = info;
    }
    public No getProx() {
        return prox;
    }
    public void setProx(No prox) {
        this.prox = prox;
    }
    

    
}
