package br.unipar;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner opcoes = new Scanner(System.in);

        while (true) {
            System.out.println("COM QUAL TABELA VOCÊ DESEJA OPERAR:");
            System.out.println("1- USUARIOS");
            System.out.println("2- CLIENTES");
            System.out.println("3- PRODUTOS");
            System.out.println("4- VENDAS");
            System.out.println("5- Sair do programa");
            System.out.println("Selecione a operação que deseja executar:");

            int opcao = opcoes.nextInt();
            opcoes.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    funcoesUsuario.usuario();
                    break;
                case 2:
                    funcoesCliente.clientes();
                    break;
                case 3:
                    funcoesProduto.produto();
                    break;
                case 4:
                    funcoesVenda.venda();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}