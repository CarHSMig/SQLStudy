package br.unipar;

import java.sql.*;
import java.util.Scanner;

public class funcoesUsuario {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
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
                    cadastrarUsuario();
                    break;
                case 2:
                    listarTodosUsuarios();
                    break;
                case 3:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 4:
                    excluirTabelaUsuario();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }


    public static void cadastrarUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.println("Escreva o nome de Usuario:");
        String username = input.nextLine();

        System.out.println("Escreva a senha do usuario:");
        String password = input.nextLine();

        System.out.println("Escreva o Nome do Usuario:");
        String nome = input.nextLine();

        System.out.println("Escreva a data de Nascimento (ex: 1890-01-01)");
        String dataNascimento = input.nextLine();

        try {
            inserirUsuario(username, password, nome, dataNascimento);
        } catch (RuntimeException e) {
            System.err.println("Erro ao inserir usuário: " + e.getMessage());
        }
    }

    public static void inserirUsuario(String username, String password, String nome, String dataNascimento) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO usuarios (username, password, nome, nascimento) VALUES(?,?,?,?)"
            );
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, Date.valueOf(dataNascimento));
            preparedStatement.executeUpdate();
            System.out.println("Usuario inserido com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosUsuarios() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");
            while (result.next()) {
                System.out.println(result.getInt("codigo") + ", " +
                        result.getString("username") + ", " +
                        result.getString("nome") + ", " +
                        result.getDate("nascimento"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirTabelaUsuario() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS usuarios");
            System.out.println("TABELA EXCLUÍDA");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}