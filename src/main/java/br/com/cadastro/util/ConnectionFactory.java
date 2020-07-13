package br.com.cadastro.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionFactory {

	private static final String DRIVER = "org.postgresql.Driver";
	private static final String URL = "jdbc:postgresql://localhost:5432/bd_cadastro_banco";
	private static final String USER = "postgres";
	private static final String PASSWORD = "1234";
	
	public static Connection getConnection(){
		try {
			Class.forName(DRIVER);
			return DriverManager.getConnection(
					URL, USER, PASSWORD);
		} catch (Exception e){
			System.err.println("Erro na conexão");
			e.printStackTrace();
		}
		return null;
	}
	
	public static void encerrarConexao(Connection connection,
			Statement statement, ResultSet result){
		encerrar(connection, statement, result);
	}
	
	public static void encerrarConexao(Connection connection,
			Statement statement){
		encerrar(connection, statement, null);
	}
	
	private static void encerrar(Connection connection,
			Statement statement, ResultSet result){
		try {
			if (result != null)
				result.close();
			if (statement != null)
				statement.close();
			if (connection != null)
				connection.close();
		} catch (Exception e){
			System.err.println("Erro no encerramento da conexão");
			e.printStackTrace();
		}
		
	}
	
}
