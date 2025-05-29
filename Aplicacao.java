import java.util.Scanner;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Random;
public class Aplicacao {
    public static void main(String[]args){
        Scanner sc = new Scanner(System.in);
        Random rd=new Random();
        int n=100;
        int limitef =10;
        int limitep=5;
        Fila f=new Fila(limitef);
        Pilha p=new Pilha(limitep);
        int sOcupacaoFila=0;
        int sLatenciaFila=0;
        int sOcupacaoPilha=0;
        int sLatenciaPilha=0;
        int i =0;

        for(int j=0;j<n;j++){
            int op= rd.nextInt(100);
            // inserir
            if(op<60){
                
                try{
                    f.insere(new Documento("doc"+i, "user"+rd.nextInt(5)));
                    System.out.println("chamado doc"+i+"na fila de impressão");
                    i++;
                }
                catch(Exception e ){
                    System.out.println(e);
                    continue;
                }
            }else if (op<95) {
                // remover
                try{
                    Documento d = f.remove();
                    System.out.println("imprimiu "+d );
                    sLatenciaFila+=d.diferenca();
                    if(op%3==0){ //1 em cada3 impressoes tem que sser reimprimida
                        p.push(new Documento(d.getNome(),d.getUsuario()));
                        System.out.println("chamado "+d.getNome()+"na   pilha  de reimpressão");
                    }
                }catch(Exception e ){

                    System.out.println(e);
                    continue;
                }
            }else{
                // Busca

                System.out.println("FIla " +f);
                System.out.println("pilha"+p);
                System.out.print("digite um arquivo");
                String nome= sc.nextLine();
                RespostaBusca resp= f.buscaPorNome(nome);
                if(resp==null){
                    System.out.println("nao encontrou documento");
                }else{
                    LocalTime horario = resp.getDocumento().getEntrada().atZone(ZoneId.systemDefault()).toLocalTime();

                    System.out.println("O documento " + resp.getDocumento().getNome() +
                            ", inserido em " + horario +
                            ", esta na posicao " + resp.getPosicao() + "da fila");
                        }
                        System.out.println("digite enter para conitnuar");
                        sc.nextLine();
                        System.out.println("ocupacao: " +f.getOcupacao()+"/"+f.getLimite());
                        sOcupacaoFila+=f.getOcupacao();
            }

        }
        while(!f.vazia()){
            try{
                    Documento d = f.remove();
                    System.out.println("imprimiu "+d );
                    sLatenciaFila+=d.diferenca();
                }catch(Exception e ){
                    System.out.println(e);
                    continue;
                }
        }
        System.out.println("relatorio");
        System.out.println("Ocupacao media: " +(sOcupacaoFila*1.0/n));
        System.out.println("Latencia: " +(sLatenciaFila*1.0/n));
    }
}
