package fr.inetum.formation.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TestConnexionWithPropertiesFile {

	static String driver;
	static String url;
	static String username;
	static String password;
	
	public static void main(String[] args) {
		//Chargement du fichier de propriétés
		ResourceBundle props = ResourceBundle.getBundle("dbParams");
		
		//Recupération des valeurs des clés du fichier de propriétés
		driver = props.getString("mysql.driver");
		url = props.getString("mysql.url");
		username = props.getString("mysql.username");
		password = props.getString("mysql.password");
		
		try {
			Class.forName(driver);
			System.out.println("DRIVER OK");
			
			try(Connection connection = DriverManager.getConnection(url, username, password)){
				System.out.println("CONNEXION OK");				
			} catch (SQLException e) {
				System.out.println("CONNEXION NOK");	
			}			
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NOK");
		}
	}
}
