package org.casaFinanceira.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/casaFinanceira";
	private static final String USER = "postgres";
	private static final String PASSWORD = "357357";

	public static Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(DRIVER);
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (Exception e) {
			System.out
					.println("Erro na conexão com o Banco de Dados postgresql.\n Contate a equipe de suporte!");
			System.out.println("Descrição do erro: " + e.getMessage());
			JOptionPane
					.showMessageDialog(
							null,
							"Erro na conexão com o Banco de Dados postgresql.\n Contate a equipe de suporte! \n"
									+ " Descrição do erro: \n" + e.getMessage());
		}
		return connection;
	}

	public static void fecharConexao(Connection connection,
			Statement statement, ResultSet result) {
		fechar(connection, statement, result);
	}

	public static void fecharConexao(Connection connection, Statement statement) {
		fechar(connection, statement, null);
	}

	private static void fechar(Connection connection, Statement statement,
			ResultSet result) {
		try {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();

		} catch (Exception e) {
			System.out
					.println("Erro no encerramento da conexão com o postgresql.");
			System.out.println("Descrição do erro: " + e.getMessage());
		}
	}

}
