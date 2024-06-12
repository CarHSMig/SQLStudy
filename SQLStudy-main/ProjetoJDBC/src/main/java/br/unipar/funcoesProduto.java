package br.unipar;

import java.sql.*;
import java.util.Scanner;

public class funcoesProduto {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void produto() {
        Scanner opcoesIns = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione a operação que deseja realizar na tabela clientes:");
            System.out.println("1- Inserir Produtos");
            System.out.println("2- listar Produtos");
            System.out.println("3- alterar Produtos");
            System.out.println("4- deletar Produtos");
            System.out.println("5- Sair");

            int opcao = opcoesIns.nextInt();
            opcoesIns.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;
                case 2:
                    listarTodosProduto();
                    break;
                case 3:
                    alterarProduto();
                    break;
                case 4:
                    excluirTabelaProduto();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarProduto() {
        Scanner input = new Scanner(System.in);

        System.out.println("Escreva a descrição do produto:");
        String descricao = input.nextLine();

        System.out.println("Escreva o valor do produto: ");
        String valor = input.nextLine();

        try {
            inserirProduto(descricao, valor);
        } catch (RuntimeException e) {
            System.err.println("Erro ao inserir o produto: " + e.getMessage());
        }
    }

    public static void inserirProduto(String descricao, String valor) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO produto (descricao, valor) VALUES(?,?)");
            preparedStatement.setString(1, descricao);
            preparedStatement.setString(2, valor);
            preparedStatement.executeUpdate();
            System.out.println("Produto inserido com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosProduto() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM produto");
            while (result.next()) {
                System.out.println(result.getInt("id_produto") + ", " + result.getString("descricao") + ", " + result.getDate("valor"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarProduto() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o ID do produto que deseja alterar:");
        int id = input.nextInt();
        input.nextLine(); // consume the newline

        System.out.println("Escreva a nova descrição do produto:");
        String novaDescricao = input.nextLine();

        System.out.println("Escreva o novo valor do produto:");
        String novoValor = input.nextLine();

        try {
            atualizarProduto(id, novaDescricao, novoValor);
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    public static void atualizarProduto(int id, String novaDescricao, String novoValor) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE produto SET descricao = ?, valor = ? WHERE id_produto = ?");
            preparedStatement.setString(1, novaDescricao);
            preparedStatement.setString(2, novoValor);
            preparedStatement.setInt(3, id);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Produto atualizado com sucesso");
            } else {
                System.out.println("Nenhum produto encontrado com o ID fornecido");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void excluirTabelaProduto() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS produto");
            System.out.println("TABELA EXCLUÍDA");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
