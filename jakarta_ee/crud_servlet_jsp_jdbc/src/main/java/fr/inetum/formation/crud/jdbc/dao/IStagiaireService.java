package fr.inetum.formation.crud.jdbc.dao;

import java.sql.SQLException;
import java.util.*;

import fr.inetum.formation.crud.jdbc.models.Stagiaire;

public interface IStagiaireService {

	/**
	 * Permet de recuperer la liste de tous les stagiaires presents en BDD
	 * 
	 * @return
	 * @throws ClassNotFoundException Si les drivers sont absents du classpath
	 * @throws SQLException           Si un probleme SQL est detecté
	 */
	List<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException;

	/**
	 * Permet de persister un Stagiaire en BDD
	 * 
	 * @param stagiaire Le stagiaire à persister
	 * @throws ClassNotFoundException Si les drivers sont absents du classpath
	 * @throws SQLException           Si un probleme SQL est detecté
	 */
	void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;

	/**
	 * Permet de supprimer le stagiaire d'id celui passé en parametre
	 * 
	 * @param id Id du stagiaire à supprimer
	 * @throws ClassNotFoundException Si les drivers sont absents du classpath
	 * @throws SQLException           Si un probleme SQL est detecté
	 */
	void deleteStagiaire(int id) throws ClassNotFoundException, SQLException;
}
