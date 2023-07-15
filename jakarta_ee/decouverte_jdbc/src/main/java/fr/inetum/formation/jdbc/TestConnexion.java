package fr.inetum.formation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexion {

	public static void main(String[] args) {
		Connection connection = null;		
		try {
			//1 -> Chargement du Driver de la BDD
			Class.forName("com.mysql.cj.jdbc.Driver");			
			System.out.println("DRIVER OK");
			
			//2 -> ON ETABLIT LA CONNEXION A LA BDD
			final String URL = "jdbc:mysql://localhost:3306/inetum_db";
			final String USER = "root";
			final String PASSWORD = "";			
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("CONNEXION OK");	
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NOK");			
		} catch (SQLException e) {
			System.out.println("CONNEXION NOK");
		}finally {
			//3 -> ON FERME LA CONNEXION APRES USAGE
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}			
		}
	}
}
