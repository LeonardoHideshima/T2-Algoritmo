public class PilhaVaziaException extends Exception{
    public PilhaVaziaException(String operacao) {
        super("Pilha Vazia. Falha em " + operacao + ".");
    }
}
