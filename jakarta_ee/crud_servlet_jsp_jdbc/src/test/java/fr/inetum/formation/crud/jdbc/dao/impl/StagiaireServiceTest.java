package fr.inetum.formation.crud.jdbc.dao.impl;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.inetum.formation.crud.jdbc.dao.IStagiaireService;
import fr.inetum.formation.crud.jdbc.models.Stagiaire;

class StagiaireServiceTest {

	private IStagiaireService service;
	private Connection connection;
	private final String URL_H2 = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; 
	
	@BeforeEach
	void setUp() throws Exception {
		connection = DriverManager.getConnection(URL_H2);
		service = new StagiaireService(connection);
		
		//Creation de table stagiaire
		String requete = "CREATE TABLE stagiaire(id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "prenom varchar(20) NOT NULL,"
				+ "email varchar(80) NOT NULL,"
				+ "mdp text NOT NULL,"
				+ "ddn date DEFAULT NULL"
				+ ")";
		
		try(Statement statement = connection.createStatement()){
			statement.execute(requete);
		}
	}

	@AfterEach
	void tearDown() throws Exception {
		connection = DriverManager.getConnection(URL_H2);
		String requete = "drop table stagiaire";
		try(Statement statement = connection.createStatement()){
			statement.execute(requete);
		}
	}

	@Test
	void testAddStagiaire() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);
		
		Stagiaire stagiaire = new Stagiaire("Joachim", "joachim@email.fr", LocalDate.now(), "Password");
		service.addStagiaire(stagiaire);
		
		stagiaire = new Stagiaire("Victor", "victor@email.fr", LocalDate.now(), "123");
		service.addStagiaire(stagiaire);
		
		List<Stagiaire> stagiaires = service.allStagiaires();
		assertEquals(2, stagiaires.size());
	}
}
