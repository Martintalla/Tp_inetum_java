package tp.utiles;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe Singleton permettant de gerer l'acces a une BDD
 * 
 * @author Victor Sicard
 *
 */
public class ConnexionSingleton {

	private static ConnexionSingleton instanceSingleton;
	private static Connection connectionAssociee;

	private ConnexionSingleton() throws ClassNotFoundException, SQLException {
		SimpleDataSource.init("dataBaseParameters");
		connectionAssociee = SimpleDataSource.getConnection();
	}

	/**
	 * Permet de recuperer l'instance unique de la classe
	 * 
	 * @return L'unique instance de la classe
	 * @throws ClassNotFoundException Si le driver de la BDD est introuvable
	 * @throws SQLException           Si un probleme de connexion SQL survient
	 */
	public static synchronized ConnexionSingleton getInstanceSingleton() throws ClassNotFoundException, SQLException {
		if (instanceSingleton == null) {
			instanceSingleton = new ConnexionSingleton();
		}
		return instanceSingleton;
	}

	/**
	 * Permet de recuperer un connexion java.sql
	 * 
	 * @return La connexion java.sql
	 */
	public static Connection getConnectionAssociee() {
		return connectionAssociee;
	}
}
