package fr.inetum.formation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import fr.inetum.formation.jdbc.utils.SimpleDataSource;

public class TestSimpleDataSouce {

	public static void main(String[] args) {
		try {
			SimpleDataSource.init("dbParams");
			System.out.println("DRIVER OK");
			
			try(Connection connection = SimpleDataSource.getConnection()){
				System.out.println("CONNEXION OK");				
			} catch (SQLException e) {
				System.out.println("CONNEXION NOK");
			}
		} catch (ClassNotFoundException e) {
			System.out.println("DRIVER NOK");
		}
	}
}
