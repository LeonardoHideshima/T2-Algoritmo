import java.time.Instant;
import java.time.Duration;
public class Documento{
    private String nome;
    private String usuario;
    private Instant entrada;
    private Instant saida;

    public Documento(String nome, String usuario) {
        this.nome = nome;
        this.usuario = usuario;
        this.entrada = Instant.now();
    }

    public String getNome() {
        return nome;
    }

    public String getUsuario() {
        return usuario;
    }

    public Instant getEntrada() {
        return entrada;
    }

    public Instant getSaida() {
        return saida;
    }
    
    public void imprime()
    {
        this.saida= Instant.now();
    }
    public long diferenca(){
        Duration d = Duration.between(entrada,saida);
        return d.getNano()/1000; 
        // para dar a resposta em microsegundo
    }

    @Override
    public String toString() {
        return "Documento [nome=" + nome + ", usuario=" + usuario + ", entrada=" + entrada + ", saida=" + saida
                + ", diferenca()=" + diferenca() + "]";
    }
    

}