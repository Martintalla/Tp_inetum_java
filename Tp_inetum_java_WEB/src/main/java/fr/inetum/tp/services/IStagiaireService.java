package fr.inetum.tp.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import fr.inetum.tp.entities.Adresse;
import fr.inetum.tp.entities.Stagiaire;



public interface IStagiaireService {
	
	/**
	 * retourne la liste des stagiaires de la table stagiaire.
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	Set<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException;
	
	/**
	 * 
	 * retourne la liste des stagiaires résidant à l’adresse passée en argument.
	 * @param adresse
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	Set<Stagiaire> allStagiaires (Adresse adresse) throws ClassNotFoundException, SQLException;
	 /**
	  * Permet de supprimer de la table stagiaire, le stagiaire passé en paramètre.
	  * @param stagiaire
	  * @throws ClassNotFoundException
	  * @throws SQLException
	  */
	
	void removeStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;
	
	/**
	 * Permet de persister un Stagiaire en BDD
	 * 
	 * @param stagiaire Le stagiaire à persister
	 * @throws ClassNotFoundException Si les drivers sont absents du classpath
	 * @throws SQLException           Si un probleme SQL est detecté
	 */
	void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;
	
	/**
	 * Permet de retourner le stagiaire de la table stagiaire, 
	 * dont les login et mot de passe sont passées en argument de la méthode.
	 * @param email
	 * @param mdp
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	
	Stagiaire getStagiaire(String email, String mdp) throws ClassNotFoundException, SQLException;
	
	/**
	 * Recherche un stagiaire à partir de son identifiant passé en paramètre.
	 * @param id
	 * @return
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	
	
	Stagiaire getStagiaire(Integer id) throws ClassNotFoundException, SQLException;
	
	/**
	 * supprime un stagiaire par id
	 * @param id
	 * @throws SQLException 
	 */

	void removeStagiaire(int id) throws SQLException;
	
	/**
	 * Vérifie si un utilisateur est présent dans la base de données
	 * @param email
	 * @param mdp
	 * @return
	 */

	public boolean verifyCredentials(String email, String mdp);
}
