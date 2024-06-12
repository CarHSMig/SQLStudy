package br.unipar;

import java.sql.*;
import java.util.Scanner;

public class funcoesVenda {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void venda() {
        Scanner opcoesIns = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione a operação que deseja realizar na tabela clientes:");
            System.out.println("1- Inserir Venda");
            System.out.println("2- listar Venda");
            System.out.println("3- alterar Venda");
            System.out.println("4- deletar Venda");
            System.out.println("5- Sair");

            int opcao = opcoesIns.nextInt();
            opcoesIns.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarVenda();
                    break;
                case 2:
                    listarTodosVenda();
                    break;
                case 3:
                    alterarVenda();
                    break;
                case 4:
                    excluirTabelaVenda();
                    break;
                case 5:
                    System.out.println("Saindo do programa...");
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    public static void cadastrarVenda() {
        Scanner input = new Scanner(System.in);

        System.out.println("Escreva o nome do cliente da venda:");
        String cliente = input.nextLine();

        System.out.println("Qual o produto da venda: ");
        String produto = input.nextLine();

        try {
            inserirVenda(cliente, produto);
        } catch (RuntimeException e) {
            System.err.println("Erro ao inserir a venda: " + e.getMessage());
        }
    }

    public static void inserirVenda(String cliente, String produto) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO venda (cliente, produto) VALUES(?,?)");
            preparedStatement.setString(1, cliente);
            preparedStatement.setString(2, produto);
            preparedStatement.executeUpdate();
            System.out.println("Venda inserida com sucesso");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void listarTodosVenda() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM venda");
            while (result.next()) {
                System.out.println(result.getInt("id_venda") + ", " + result.getString("cliente") + ", " + result.getDate("produto"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void alterarVenda() {
        Scanner input = new Scanner(System.in);

        System.out.println("Digite o ID da venda que deseja alterar:");
        int id = input.nextInt();
        input.nextLine(); // consume the newline

        System.out.println("Escreva o novo nome do cliente da venda:");
        String novoCliente = input.nextLine();

        System.out.println("Escreva qual o produto da venda:");
        String novoProduto = input.nextLine();

        try {
            atualizarProduto(id, novoCliente, novoProduto);
        } catch (RuntimeException e) {
            System.err.println("Erro ao atualizar o produto: " + e.getMessage());
        }
    }

    public static void atualizarProduto(int id, String novoCliente, String novoProduto) {
        try {
            Connection conn = connection();
            PreparedStatement preparedStatement = conn.prepareStatement("UPDATE venda SET cliente = ?, produto = ? WHERE id_venda = ?");
            preparedStatement.setString(1, novoCliente);
            preparedStatement.setString(2, novoProduto);
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

    public static void excluirTabelaVenda() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            statement.executeUpdate("DROP TABLE IF EXISTS venda");
            System.out.println("TABELA EXCLUÍDA");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
