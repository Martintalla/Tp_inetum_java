package fr.inetum.tp.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Classe permet d'obtenir un connexion à une BDD
 * 
 * @author Martin TALLA
 * @version 0.0.1 du 07-07-2023 *
 */
public class SimpleDataSource {

	static String driver;
	static String url;
	static String username;
	static String password;

	/**
	 * Permet d'initialiser les parametres de connexion à la BDD depuis le fichier
	 * de proprietes
	 * 
	 * @param nomFichier Le nom du fichier de proprietes contenant les params de
	 *                   connexion
	 * @throws ClassNotFoundException Si le driver de la BDD est introuvable
	 */
	public static void init(String nomFichier) throws ClassNotFoundException {
		ResourceBundle props = ResourceBundle.getBundle(nomFichier);

		driver = props.getString("mysql.driver");
		Class.forName(driver);

		url = props.getString("mysql.url");
		username = props.getString("mysql.username");
		password = props.getString("mysql.password");
	}

	public static void init(String nomFichier, String driverKey, String urlKey, String usernameKey,
			String passwordKey) {

	}

	/**
	 * Permet d'etablir une connection à la BDD à partir des parametres de connexion
	 * 
	 * @return La connexion java.sql à la BDD
	 * @throws SQLException Si un problème survient lors de l'ebalissement à la
	 *                      connexion
	 */
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}
}
