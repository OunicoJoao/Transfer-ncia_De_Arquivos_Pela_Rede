package program;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Conexao {
    private File origem;
    private File destino;
    private Arquivo arqv;

    private String ip_servidor;
    private int porta;

    public Conexao(String ip_servidor,int porta){
        super();
        this.ip_servidor = ip_servidor;
        this.porta = porta;
    }

    public Conexao(int porta){
        super();
        this.porta = porta;
    }

    //função para enviar
    //o send é um cliente que envia dados na conexão
    public void send_file(){
        try(Socket cliente = new Socket(this.ip_servidor, this.porta)){
            //ler o arquivo
            Arquivo arquivo = this.read_file();

            //no contexto desse sistema apenas usando java, transferir objeto serialize é muito mais simples
            // ou teria que usar data ‘output’ stream e data ‘input’ para transferir de maneira mais robusta
            // e segura. Mas como o foco é para pequenos arquivos, e transferencia apenas para meus dois computadores locais
            // funciona perfeitamente e é super-rápido de implantar a ideia
            ObjectOutputStream oos = new ObjectOutputStream(cliente.getOutputStream());

            oos.writeObject(arquivo);

            System.out.println("Arquivo enviado!");

            //fechando stream
            oos.close();
        } catch (Exception e) {
            System.out.println("Erro: "+e);
        }
    }

    //função para receber
    //receive é um servidor esperando conexão
    public void receive_file(){
        //criando servidor na porta xxx
        try(ServerSocket servidor = new ServerSocket(this.porta)){
            System.out.println("Aguardando envio do arquivo...");
            //estabelecendo conexão com um cliente
            Socket cliente = servidor.accept();//aguardando acessar
            System.out.println("Conexão concluída.");

            ObjectInputStream ois = new ObjectInputStream(cliente.getInputStream());

            this.arqv = (Arquivo) ois.readObject();

            //escrevendo o arquivo no computador
            this.write_file();
            System.out.println("Arquivo "+arqv.getNome()+" salvo em: "+destino+"!");
            //fechando stream
            ois.close();
        }catch (Exception e){
            System.out.println("Erro ao receber arquivo:" + e);
        }
    }

    //essa leitura será feita com arquivos pequenos e médios
    //por isso uso readAllBytes
    //caso contrario deveria enviar os dados por partes, em blocos
    public Arquivo read_file() {
        Arquivo arq = new Arquivo();
        //verificando se o diretorio existe e o arquivo também
        if(this.origem.exists() && this.origem.isFile()){
            try (FileInputStream fis = new FileInputStream(this.origem)) {
                arq.setNome(this.origem.getName());
                arq.setConteudo(fis.readAllBytes());
            } catch (Exception e) {
                System.out.println("Erro: " + e);
            }
            System.out.println("Arquivo lido! Nome: " + arq.getNome());
            return arq;
        }else {
            System.out.println("Erro! Arquivo inexistente ou caminho incorreto.");
        }
        return null;
    }

    public void write_file(){
        //caso o destino não exista ele cria o diretorio, evitando erros
        if(!this.destino.exists()){
            if(!this.destino.mkdirs()){
                System.out.println("Erro ao criar o diretorio de destino.");
                return;
            }
        }
        try(FileOutputStream fos = new FileOutputStream(this.destino.getPath()+"/"+this.arqv.getNome())){
            //escrever os bytes no novo local
            fos.write(this.arqv.getConteudo());
        }catch(Exception e){
            System.out.println("Erro: " + e);
        }
        System.out.println("Arquivo salvo em "+this.destino.getPath()+" !");
    }

    public File getOrigem() {
        return origem;
    }
    public void setOrigem(File origem) {
        this.origem = origem;
    }
    public void setOrigem(String origem) {
        this.origem = new File(origem);
    }

    public File getDestino() {
        return destino;
    }
    public void setDestino(File destino) {
        this.destino = destino;
    }
    public void setDestino(String destino) {
        this.destino = new File(destino);
    }


    public Arquivo getArqv() {
        return arqv;
    }

    public void setArqv(Arquivo arqv) {
        this.arqv = arqv;
    }

    public String getIp_servidor() {
        return ip_servidor;
    }

    public void setIp_servidor(String ip_servidor) {
        this.ip_servidor = ip_servidor;
    }

    public int getPorta() {
        return porta;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }
}
