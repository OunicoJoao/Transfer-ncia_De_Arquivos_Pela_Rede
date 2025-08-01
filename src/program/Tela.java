package program;

import java.util.Scanner;

public class Tela {

    private final Scanner read;

    public Tela(Scanner scanner){
        super();
        this.read = scanner;
    }

    public int lerOpcao(int min, int max) {
        int escolha = 0;
        do {
            System.out.print(">: ");
            try {
                escolha =  Integer.parseInt(read.nextLine());
                if (escolha >= min && escolha <= max) {
                    return escolha;
                }
                System.out.println("Opção inválida.");
            } catch (Exception expt) {
                System.out.println("Digite um número válido.");
            }
        }while(escolha <= min || escolha >= max);
        return -1;
    }

    public String lerString() {
        System.out.print(">: ");
        return read.nextLine().trim();
    }


    //tela de menu
    public int Menu() {
        System.out.print("""
				---------------------------------------
				|====> Transferência de Arquivos <====|
				---------------------------------------
				""");
        System.out.print("""
				>Escolha:
				 1.Enviar Arquivo
				 2.Receber Arquivo
				 3.Sair
				""");
        return lerOpcao(1,3);
    }

    public void fecharleitura(){
        read.close();
    }

}
