package fr.inetum.formation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestConnexionAutoCloseable {

	final static String DRIVER = "com.mysql.cj.jdbc.Driver";
	final static String URL = "jdbc:mysql://localhost:3306/inetum_db";
	final static String USER = "root";
	final static String PASSWORD = "";

	public static void main(String[] args) {
		try {
			Class.forName(DRIVER);
			System.out.println("DRIVER OK");

			// UTILSATION DE TRY_WITH_RESOURCES
			try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
				System.out.println("CONNEXION OK");
			} catch (SQLException e) {
				System.out.println("CONNEXION NOK");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NOK");
		}
	}
}
