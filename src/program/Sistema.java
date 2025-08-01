package program;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Sistema {
    private final ArrayList<Endereco> enderecos;
    private final Tela tela;
    private final Scanner rd;

    public Sistema(){
        super();
        this.enderecos = new ArrayList<>();
        this.rd = new Scanner(System.in);
        this.tela = new Tela(rd);
    }

    public void run(){
        this.loop();
    }

    public void stop(){
        System.out.println("Saindo...");
        rd.close();
        System.exit(0);
    }

    //melhorar os scanners, pois está dando erro
    public void loop(){
        Conexao con;
        this.carregar_enderecos();
        if (enderecos.isEmpty()) {
            System.out.println("Nenhum endereço carregado. Verifique o arquivo enderecos.txt");
        }
        int op = 0;
        while (op != -1){
            op = tela.Menu();
            if(op == 1) {
                System.out.println(">Enviar: ");
                System.out.println("-*Escolha um dos endereços para enviar: ");
                int count = 0;
                for(Endereco e: this.enderecos){
                    System.out.println(count+" - "+e.getNome() +" - ("+e.getIp()+")");
                    count++;
                }
                op = tela.lerOpcao(0,count-1);
                String ip = this.enderecos.get(op).getIp();

                System.out.println(">Digite o caminho do arquivo para enviar: ");
                String path = tela.lerString();

                System.out.println("-*Digite a porta: ");
                int porta = tela.lerOpcao(1,65535); //de 1 ao máximo de portas TCP UDP

                con = new Conexao(ip,porta);
                con.setOrigem(path);
                System.out.println(con.getOrigem());
                System.out.println(con.getPorta());
                con.send_file();

                op = 0;
            }else if(op == 2){
                System.out.println(">Receber: ");
                System.out.println("-*Digite o caminho para salvar: ");
                String destino = tela.lerString();
                System.out.println("-*Digite a porta: ");
                int porta = tela.lerOpcao(1,65535);
                con = new Conexao(porta);
                con.setDestino(destino);
                con.receive_file();
                op = 0;
            }else{
                op = -1;
            }
        }
        this.stop();
    }

    //carregar ip e portas para envio de arquivos
    private void carregar_enderecos(){
        String arquivo = "enderecos.txt";
        try {
            BufferedReader bfr = new BufferedReader(new FileReader(arquivo));
            String linha = "";
            while((linha = bfr.readLine()) != null){
                String[] s = linha.split(";");
                enderecos.add(new Endereco(s[0],s[1]));
            }
        }catch(Exception e){
            System.out.println("Erro ao ler o arquivo de endereços "+e);
        }
    }
}
