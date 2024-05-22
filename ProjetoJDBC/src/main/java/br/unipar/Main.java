package br.unipar;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        crateTableUser();

        //inserirUsuario("Taffe", "12345", "Fabio", "1890-01-01");

        listarTodosUsuarios();
    }


    public static Connection connection() throws SQLException {
        //localhost -> Onde está o banco
        //5432 -> porta padrão do banco
        return DriverManager.getConnection(url, user, password);
    }

    public static void crateTableUser() {
        try {
            Connection conn = connection(); // em caso de problema o catch é executado // in case of problem, catch is executed

            Statement statement = conn.createStatement(); // Criar acesso para o banco para executar
            String sql = " CREATE TABLE IF NOT EXISTS usuarios ( "
                    + "codigo SERIAL PRIMARY KEY, "
                    + " username VARCHAR(50) UNIQUE NOT NULL, "
                    + " password VARCHAR(300) NOT NULL, "
                    + " nome VARCHAR(50) NOT NULL,"
                    + " nascimento DATE)";

            statement.executeUpdate(sql); // Cria a tabela no banco
            System.out.println("TABELA CRIADA");

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public static void inserirUsuario(String username, String password, String nome, String dataNascimento) {
        try {

            //Abre a conexão com o banco
            Connection conn = connection(); // em caso de problema o catch é executado // in case of problem, catch is executed

            //Prepara a execução de um SQL
            PreparedStatement preparedStatement = conn.prepareStatement(
                    "INSERT INTO usuarios (username, password, nome, nascimento)"

                            + "VALUES(?,?,?,?)"

            );

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, nome);
            preparedStatement.setDate(4, java.sql.Date.valueOf(dataNascimento));

            preparedStatement.executeUpdate();

            System.out.printf("usuario inserido com sucesso");


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static void listarTodosUsuarios(){

        try {
            Connection conn = connection();

            Statement statement = conn.createStatement();

            ResultSet result = statement.executeQuery("SELECT * FROM usuarios");

            while (result.next()) {
                System.out.println(result.getInt("codigo"));

            }
        }

        catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}
