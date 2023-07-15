package fr.inetum.formation.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

import fr.inetum.formation.jdbc.utils.MaConnexion;

public class TestMaConnexion {

	public static void main(String[] args) {
		try (Connection connection = MaConnexion.getInstance().getConnection()){
			System.out.println("Test MaConnexion OK");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Test MaConnexion NOK : " + e.getMessage());
		}
	}
}
