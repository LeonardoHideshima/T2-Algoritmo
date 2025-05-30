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

        //Estatísticas:
        int sOcupacaoFila = 0;
        long sLatenciaFila= 0;
        int sOcupacaoPilha= 0;
        long sLatenciaPilha= 0;
        int cInsFilaOk = 0;
        int cInsFilaFail = 0;
        int cRemFilaOk = 0;
        int cRemFilaFail = 0;
        int cInsPilhaOk = 0;
        int cInsPilhaFail = 0;
        int cRemPilhaOk = 0;
        int cRemPilhaFail = 0;
        int cBusca = 0;
        int i =0;

        for(int j=0;j<n;j++){
            int op= rd.nextInt(100);
            // inserir Fila
            if(op<50){ //op de 0 ate 49 insere na fila
                
                try{
                    f.insere(new Documento("doc"+i, "user"+rd.nextInt(5)));
                    System.out.println("chamado doc"+i+"na fila de impressão");
                    i++;
                    cInsFilaOk++;
                }
                catch(Exception e ){
                    System.out.println(e);
                    cInsFilaFail++;
                }

            }else if (op<90) {//op vai de 50 ate 89
                // remover Fila
                try{
                    Documento d = f.remove();
                    System.out.println("imprimiu "+d );
                    sLatenciaFila+=d.diferenca();
                    cRemFilaOk++;
                    if(op%3==0){ //1 em cada3 impressoes tem que ser reimpressa
                        // Inserir Pilha
                        p.push(new Documento(d.getNome(),d.getUsuario()));
                        System.out.println("chamado "+d.getNome()+"na pilha  de reimpressão");
                        cInsPilhaOk++;
                    }
                }catch(FilaVaziaException e ){
                    System.out.println(e);
                    cRemFilaFail++;
                }catch(PilhaCheiaException e ){
                    System.out.println(e);
                    cInsPilhaFail++;
                }
            }else if (op<95){//op vai de 90 ate 94
                // Busca
                System.out.println("Fila " +f);
                System.out.println("pilha "+p);
                System.out.print("digite um arquivo ");
                String nome= sc.nextLine();
                String onde = " da fila.";
                RespostaBusca resp= f.buscaPorNome(nome);
                cBusca++;
                if(resp==null){
                    resp = p.buscaPorNome(nome);

                    if (resp==null){
                        System.out.println("Documento não encontrado.");
                        System.out.println("digite enter para continuar");
                        sc.nextLine();
                        continue;
                    }
                    onde = " da pilha.";
                }
                LocalTime horario = resp.getDocumento().getEntrada().atZone(ZoneId.systemDefault()).toLocalTime();

                System.out.println("O documento " + resp.getDocumento().getNome() +
                        ", inserido em " + horario +
                        ", esta na posicao " + resp.getPosicao() + onde);

                System.out.println("digite enter para continuar");
                sc.nextLine();

            }else {//op entre 95 e 99
                //Remoção da Pilha
                try {
                    Documento d = p.pop();
                    System.out.println("reimprimiu " + d);
                    sLatenciaPilha+=d.diferenca();
                    cRemPilhaOk++;
                } catch (Exception e) {
                    System.out.println(e);
                    cRemPilhaFail++;
                }
            }
            System.out.println("ocupacao Fila: " +f.getOcupacao()+"/"+f.getLimite());
            sOcupacaoFila+=f.getOcupacao();
            System.out.println("ocupacao Pilha: " +p.getOcupacao()+"/"+p.getLimite());
            sOcupacaoPilha+=p.getOcupacao();
        }
        while(!f.vazia()){
            try{
                Documento d = f.remove();
                System.out.println("imprimiu "+d );
                sLatenciaFila+=d.diferenca();
                cRemFilaOk++;
            }catch(Exception e ){
                System.out.println(e);
            }
        }
        while(!p.vazia()){
            try{
                Documento d = p.pop();
                System.out.println("reimprimiu "+d );
                sLatenciaPilha+=d.diferenca();
                cRemPilhaOk++;
            }catch(Exception e ){
                System.out.println(e);
            }
        }
        System.out.println("\n******** Relatorio **********\n");
        System.out.println("Ocupacao media da fila: " +(sOcupacaoFila*1.0/n) + "/" + f.getLimite());
        System.out.println("Ocupacao media da pilha: " +(sOcupacaoPilha*1.0/n+ "/" + p.getLimite()));

        System.out.println("Latencia media da fila: " +(sLatenciaFila*1.0/cRemFilaOk) + " microsegs");
        System.out.println("Latencia media da pilha: " +(sLatenciaPilha*1.0/cRemPilhaOk) + " microsegs");

        System.out.println("Inserções na Fila com sucesso:" + cInsFilaOk);
        System.out.println("Inserções na Fila sem sucesso:" + cInsFilaFail);

        System.out.println("Remoções na Fila com sucesso:" + cRemFilaOk);
        System.out.println("Remoções na Fila sem sucesso:" + cRemFilaFail);

        System.out.println("Inserções na Pilha com sucesso:" + cInsPilhaOk);
        System.out.println("Inserções na Pilha sem sucesso:" + cInsPilhaFail);

        System.out.println("Remoções na Pilha com sucesso:" + cRemPilhaOk);
        System.out.println("Remoções na Pilha sem sucesso:" + cRemPilhaFail);

        System.out.println("Buscas:" + cBusca);
    }
}
