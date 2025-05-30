public class FilaCheiaException extends Exception{
    public FilaCheiaException(String operacao) {
        super("Fila Cheia. Falha em " + operacao + ".");
    }
}
