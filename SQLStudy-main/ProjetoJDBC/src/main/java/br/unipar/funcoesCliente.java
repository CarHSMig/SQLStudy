package br.unipar;

import java.sql.*;
import java.util.Scanner;

public class funcoesCliente {

        private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
        private static final String user = "postgres";
        private static final String password = "admin123";

        public static Connection connection() throws SQLException {
            return DriverManager.getConnection(url, user, password);
        }

        public static void cadastrarClientes() {
            Scanner input = new Scanner(System.in);

            System.out.println("Escreva o Nome do Cliente:");
            String nome = input.nextLine();

            System.out.println("Escreva o cpf do Cliente");
            String cpf = input.nextLine();

            try {
                inserirCliente(nome,cpf);
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

    public static void listarTodosClientes(){
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");
            while (result.next()) {
                System.out.println(result.getInt("id_cliente") + ", " +
                        result.getString("nome") + ", " +
                        result.getString("cpf"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void excluirTabelaClientes(){
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

