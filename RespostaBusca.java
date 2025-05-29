public class RespostaBusca {
    private Documento documento;
    private int posicao;
    public RespostaBusca(Documento documento, int posicao) {
        this.documento = documento;
        this.posicao = posicao;
    }
    public Documento getDocumento() {
        return documento;
    }
    public int getPosicao() {
        return posicao;
    }

    
}
