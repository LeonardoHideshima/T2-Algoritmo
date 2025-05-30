public class FilaVaziaException extends Exception{
    public FilaVaziaException(String operacao) {
        super("Fila Vazia. Falha em " + operacao + ".");
    }
}
