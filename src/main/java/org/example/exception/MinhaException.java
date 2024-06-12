package org.example.exception;

import java.util.Scanner;

public class MinhaException extends RuntimeException{

    public MinhaException(String mensagem){
        super(mensagem);
    }
    public MinhaException(){}

    public int leitura(String menu, int cont) {
        int opcao;
        int tentativas = cont;
        Scanner leitura = new Scanner(System.in);

        System.out.println(menu);
        if (tentativas < 3) {
            try {
                opcao = leitura.nextInt();
            } catch (Exception e) {
                leitura.nextLine();
                System.out.println("ENTRADA INVÁLIDA");
                tentativas++;
                opcao = leitura(menu, tentativas);
            }
            return opcao;
        }else{
            throw new RuntimeException("NUMERO DE TENTATIVAS EXCEDIDO");
        }
    }
}
