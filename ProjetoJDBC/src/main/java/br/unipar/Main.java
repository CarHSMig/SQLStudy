package br.unipar;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:postgresql://localhost:5432/exemplo1";
    private static final String user = "postgres";
    private static final String password = "admin123";

    public static void main(String[] args) {
        crateTableUser();
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
}
