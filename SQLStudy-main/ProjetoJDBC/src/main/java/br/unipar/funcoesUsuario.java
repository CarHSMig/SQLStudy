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
                    alterarUsuario();
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
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO usuarios (username, password, nome, nascimento) VALUES(?,?,?,?)");
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
                System.out.println(result.getInt("codigo") + ", " + result.getString("username") + ", " + result.getString("nome") + ", " + result.getDate("nascimento"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarUsuario() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o código do usuário que deseja alterar:");
        int codigo = input.nextInt();
        input.nextLine(); // consume the newline

        System.out.println("Escreva o novo nome de usuário:");
        String novoUsername = input.nextLine();

        System.out.println("Escreva a nova senha do usuário:");
        String novaPassword = input.nextLine();

        System.out.println("Escreva o novo nome do usuário:");
        String novoNome = input.nextLine();

        System.out.println("Escreva a nova data de nascimento (ex: 1890-01-01):");
        String novaDataNascimento = input.nextLine();

        try {
            atualizarUsuario(codigo, novoUsername, novaPassword, novoNome, novaDataNascimento);
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar usuário: " + e.getMessage());
        }
    }

    public static void atualizarUsuario(int codigo, String novoUsername, String novaPassword, String novoNome, String novaDataNascimento) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE usuarios SET username = ?, password = ?, nome = ?, nascimento = ? WHERE codigo = ?");
            preparedStatement.setString(1, novoUsername);
            preparedStatement.setString(2, novaPassword);
            preparedStatement.setString(3, novoNome);
            preparedStatement.setDate(4, Date.valueOf(novaDataNascimento));
            preparedStatement.setInt(5, codigo);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Usuário atualizado com sucesso");
            } else {
                System.out.println("Nenhum usuário encontrado com o código fornecido");
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