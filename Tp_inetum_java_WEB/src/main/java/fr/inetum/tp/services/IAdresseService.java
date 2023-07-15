package fr.inetum.tp.services;

import java.sql.SQLException;
import java.util.Set;

import fr.inetum.tp.entities.Adresse;



public interface IAdresseService {

	/**
	 * retourne la liste des adressses de la table adresse.
	 */
	Set<Adresse> allAdresses() throws ClassNotFoundException, SQLException;
	
	/**
	 * retourne l’adresse dont l’id est passée en paramètres
	 * @param idAdresse
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	Adresse readAdresses(int idAdresse) throws ClassNotFoundException, SQLException;
	/**
	 * Persiste l’adresse passé en paramètre en base de données
	 * @param adresse
	 * @return 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	Adresse addAdresse(Adresse adresse) throws ClassNotFoundException, SQLException;
	
	
	

	int dernierIdTableAdresse() throws ClassNotFoundException, SQLException;

	void modifierAdresse(Adresse adresse) throws ClassNotFoundException, SQLException;

}
