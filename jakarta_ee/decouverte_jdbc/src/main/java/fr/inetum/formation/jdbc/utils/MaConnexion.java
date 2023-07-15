package fr.inetum.formation.jdbc.utils;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe Singleton permettant de gerer l'acces a une BDD
 * 
 * @author Joachim Zadi
 *
 */
public class MaConnexion {

	private static MaConnexion instance;
	private Connection connection;

	private MaConnexion() throws ClassNotFoundException, SQLException {
		SimpleDataSource.init("dbParams");
		connection = SimpleDataSource.getConnection();
	}

	/**
	 * Permet de recuperer l'instance unique de la classe
	 * 
	 * @return L'unique instance de la classe
	 * @throws ClassNotFoundException Si le driver de la BDD est introuvable
	 * @throws SQLException           Si un probleme de connexion SQL survient
	 */
	public static synchronized MaConnexion getInstance() throws ClassNotFoundException, SQLException {
		if (instance == null) {
			instance = new MaConnexion();
		}
		return instance;
	}

	/**
	 * Permet de recuperer un connexion java.sql
	 * 
	 * @return La connexion java.sql
	 */
	public Connection getConnection() {
		return connection;
	}
}
