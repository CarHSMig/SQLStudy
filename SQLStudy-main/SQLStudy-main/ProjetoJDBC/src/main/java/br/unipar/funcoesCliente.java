package br.unipar;

import java.sql.*;
import java.util.*;

public class funcoesCliente {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
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
                    cadastrarClientes();
                    break;
                case 2:
                    listarTodosClientes();
                    break;
                case 3:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 4:
                    excluirTabelaClientes();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarClientes() {
        Scanner input = new Scanner(System.in);

        System.out.println("Escreva o Nome do Cliente:");
        String nome = input.nextLine();

        System.out.println("Escreva o cpf do Cliente");
        String cpf = input.nextLine();

        try {
            inserirCliente(nome, cpf);
        } catch (RuntimeException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public static void inserirCliente(String nome, String cpf) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO cliente (nome, cpf) VALUES(?,?)"
            );
            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, cpf);
            preparedStatement.executeUpdate();
            System.out.println("Cliente inserido com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosClientes() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM cliente");
            while (result.next()) {
                System.out.println(result.getInt("id_cliente") + ", " +
                        result.getString("nome") + ", " +
                        result.getString("cpf"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirTabelaClientes() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS cliente");
            System.out.println("TABELA EXCLUÍDA");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

