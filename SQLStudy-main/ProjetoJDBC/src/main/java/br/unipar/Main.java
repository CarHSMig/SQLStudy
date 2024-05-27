package br.unipar;

import java.util.Scanner;

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
                     usuario();
                    break;
                case 2:
                    clientes();
                    break;
                case 3:
                    produtos();
                    break;
                case 4:
                    vendas();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void usuario() {
        Scanner opcoesIns = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione a operação que deseja realizar na tabela usuarios:");
            System.out.println("1- Inserir Usuarios");
            System.out.println("2- listar usuarios");
            System.out.println("3- alterar usuarios");
            System.out.println("4- deletar tabela");
            System.out.println("5- Sair");

            int opcao = opcoesIns.nextInt();
            opcoesIns.nextLine();

            switch (opcao) {
                case 1:
                    funcoesUsuario.cadastrarUsuario();
                    break;
                case 2:
                    funcoesUsuario.listarTodosUsuarios();
                    break;
                case 3:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 4:
                    funcoesUsuario.excluirTabelaUsuario();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
    public static void clientes() {
        Scanner opcoesIns = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione a operação que deseja realizar na tabela clientes:");
            System.out.println("1- Inserir clientes");
            System.out.println("2- listar clientes");
            System.out.println("3- alterar clientes");
            System.out.println("4- deletar tabela");
            System.out.println("5- Sair");

            int opcao = opcoesIns.nextInt();
            opcoesIns.nextLine();

            switch (opcao) {
                case 1:
                    funcoesCliente.cadastrarClientes();
                    break;
                case 2:
                    funcoesCliente.listarTodosClientes();
                    break;
                case 3:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 4:
                    funcoesCliente.excluirTabelaClientes();
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