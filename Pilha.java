
public class Pilha {
    private No topo;
    private int ocupacao;
    private int limite;
    public Pilha(int limite) {
        this.limite = limite;
        this.topo= null;
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

    
    public void push(Documento info) throws PilhaCheiaException{
        if(this.cheia()){
            throw new PilhaCheiaException("inserir");

        }
        No novo= new No(info);
        novo.setProx(topo);
        topo=novo;
        ocupacao++;

    
    }
    public Documento pop() throws PilhaVaziaException{
        if(this.vazia()){
            throw new PilhaVaziaException("remover");

        }
        Documento d= topo.getInfo();
        d.imprime();
        topo=topo.getProx();
        ocupacao--;
        return d;
    }

    public RespostaBusca buscaPorNome(String nome){
        if(this.vazia()){
            return null;

        }
        No aux= topo;
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
        No aux=topo;
        while(aux!= null){
            frase+= " "+ aux.getInfo().getNome();
            aux=aux.getProx();
        }
        return frase;
    }
}
