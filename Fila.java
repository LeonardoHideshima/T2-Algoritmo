public class Fila {
    private No inicio;
    private No fim;
    private int limite;
    private int ocupacao;

    public Fila(int limite) {
        this.limite = limite;
        this.inicio=null;
        this.fim=null;
        this.ocupacao=0;
    }
    public boolean vazia(){
        return ocupacao==0;
    }
    public boolean cheia(){
        return ocupacao==limite;
    }
    public int getLimite() {
        return limite;
    }
    public int getOcupacao() {
        return ocupacao;
    }

    public Documento remove() throws FilaVaziaException{
        if (this.vazia()){
            throw new FilaVaziaException("remover");
        }
        Documento resultado= inicio.getInfo();
        resultado.imprime();
        inicio=inicio.getProx();
        ocupacao--;
        return resultado;
    }

    public void insere(Documento info) throws FilaCheiaException{
        if (this.cheia()){
            throw new FilaCheiaException("inserir");
        }
        No novo=new No(info);
        if (this.vazia()){
            inicio=novo;
            fim=novo;
        }else{
            fim.setProx(novo);
            fim=novo;
        }

        ocupacao++;
    }

    public RespostaBusca buscaPorNome(String nome){
        if(this.vazia()){
            return null;

        }
        No aux= inicio;
        int pos= 1;
        while(aux!=null){
            if(aux.getInfo().getNome().equals(nome)){
                return new RespostaBusca(aux.getInfo(),pos);
            }
            aux=aux.getProx();
            pos++;
        }
        return null;

        
    }
    
    public String toString(){
        String frase = "";
        No aux=inicio;
        while(aux!= null){
            frase+= " "+ aux.getInfo().getNome();
            aux=aux.getProx();
        }
        return frase;
    }

}
