public class PilhaCheiaException extends Exception{
    public PilhaCheiaException(String operacao) {
        super("Pilha Cheia. Falha em " + operacao + ".");
    }
}
