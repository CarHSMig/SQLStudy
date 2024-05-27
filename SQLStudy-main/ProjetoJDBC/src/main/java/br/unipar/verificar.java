package br.unipar;
import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static Connection connection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static void main(String[] args) {
        Scanner opcoes = new Scanner(System.in);

        while (true) {
            System.out.println("MENU:");
            System.out.println("1- Criar Tabela");
            System.out.println("2- Inserir dados em uma tabela");
            System.out.println("3- Listar Tabela");
            System.out.println("4- Excluir Tabela");
            System.out.println("5- Sair do programa");
            System.out.println("Selecione a operação que deseja executar:");

            int opcao = opcoes.nextInt();
            opcoes.nextLine(); // Limpar o buffer de entrada

            switch (opcao) {
                case 1:
                    criarTabelaUsuario();
                    break;
                case 2:
                    inserirNaTabela();
                    break;
                case 3:
                    listarTodosUsuarios();
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

    public static void criarTabelaUsuario() {
        try {
            Connection conn = connection();
            Statement statement = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "codigo SERIAL PRIMARY KEY, "
                    + "username VARCHAR(50) UNIQUE NOT NULL, "
                    + "password VARCHAR(300) NOT NULL, "
                    + "nome VARCHAR(50) NOT NULL, "
                    + "nascimento DATE)";

            statement.executeUpdate(sql);
            System.out.println("TABELA CRIADA");
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void inserirNaTabela() {
        Scanner opcoesIns = new Scanner(System.in);
        while (true) {
            System.out.println("Selecione a tabela a qual vai inserir dados:");
            System.out.println("1- Inserir Usuarios");
            System.out.println("2- Inserir Produtos");
            System.out.println("3- Inserir Vendas");
            System.out.println("4- Inserir Clientes");
            System.out.println("5- Sair");

            int opcao = opcoesIns.nextInt();
            opcoesIns.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 3:
                    System.out.println("Funcionalidade não implementada.");
                    break;
                case 4:
                    System.out.println("Funcionalidade não implementada.");
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

        System.out.println("Escreva a data de Nascimento (ex: 1890-01-01):");
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
                    "INSERT INTO usuarios (username, password, nome, nascimento) VALUES (?, ?, ?, ?)");

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