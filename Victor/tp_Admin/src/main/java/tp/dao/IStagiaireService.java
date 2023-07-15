package tp.dao;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import tp.modeles.Adresse;
import tp.modeles.Stagiaire;

public interface IStagiaireService {
	
	/**
	 * Permet de récupérer la liste de tous les stagiaires présents en BDD
	 * 
	 * @return
	 * @throws ClassNotFoundException Si les drivers sont absents du classpath
	 * @throws SQLException Si un problème SQL est détecté 
	 */
	
	List<Stagiaire> tousStagiaires() throws ClassNotFoundException, SQLException;
	
	/**
	 * Permet de persister un stagiaire en BDD
	 * @param stagiaire
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;
	
	void deleteStagiaire(Integer id) throws ClassNotFoundException, SQLException;
	
	public void modifierStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;

	int dernierIdTableStagiaire() throws ClassNotFoundException, SQLException;

	ArrayList<Boolean> rechercherDansLaBDD(String ideEmail, String mdpe) throws ClassNotFoundException, SQLException;

	int adresseIddelIdStagiaire(int idStagiaire) throws ClassNotFoundException, SQLException;

	Boolean checkIlExisteAuMoinsUnAdmin() throws ClassNotFoundException, SQLException;

	
	/*
	 * 
	 * Méthodes demandées dans l'énoncé
	 * 
	 * 
	 */
	Set<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException;
	
	Set<Stagiaire> allStagiaires(Adresse adresse) throws ClassNotFoundException, SQLException;

	Stagiaire getStagiaire(int id) throws ClassNotFoundException, SQLException;

	void addStagiaire(Stagiaire stagiaire, Adresse adresse) throws ClassNotFoundException, SQLException;

	void updateStagiaire(Stagiaire stagiaire, Adresse adresse) throws ClassNotFoundException, SQLException;

	void removeStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException;

	Stagiaire getStagiaire(String login, String mdp) throws ClassNotFoundException, SQLException;

}
