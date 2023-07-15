package tp.test.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tp.dao.AdresseService;
import tp.dao.IAdresseService;
import tp.dao.IStagiaireService;
import tp.dao.StagiaireService;
import tp.modeles.Adresse;
import tp.modeles.Stagiaire;

class StagiaireServiceTest {

	private IStagiaireService service;
	private IAdresseService serviceAdresse;
	private Connection connection;
	private final String URL_H2 = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1"; 
	
	@BeforeEach
	void setUp() throws Exception {
		
		this.connection = DriverManager.getConnection(URL_H2);
		service = new StagiaireService(connection);
		serviceAdresse = new AdresseService(connection);
		
		//Creation de table admin
		String requeteTableAdmin = "CREATE TABLE table_tp_admin("
				+ "id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "prenom varchar(20) NOT NULL,"
				+ "email varchar(80) NOT NULL,"
				+ "mdp text NOT NULL,"
				+ "adresseid int NOT NULL,"
				+ "ddn date DEFAULT NULL,"
				+ "role varchar(20) NOT NULL )";
		
		//Creation de table adresse
		String requeteTableAdresse = "CREATE TABLE table_tp_adresse("
				+ "id int NOT NULL AUTO_INCREMENT PRIMARY KEY,"
				+ "nomvoie varchar(50) NOT NULL,"
				+ "codepostal varchar(5) NOT NULL,"
				+ "ville varchar(20) NOT NULL"
				+ ")";
		
		try(Statement statement = connection.createStatement()){
			statement.execute(requeteTableAdmin);
			statement.execute(requeteTableAdresse);

		}
		
	}

	@AfterEach
	void tearDown() throws Exception {
		
		connection = DriverManager.getConnection(URL_H2);
		
		String requeteTableAdmin = "DROP TABLE table_tp_admin";
		String requeteTableAdresse = "DROP TABLE table_tp_adresse";
		try(Statement statement = connection.createStatement()){
			statement.execute(requeteTableAdmin);
			statement.execute(requeteTableAdresse);
		}

	}
	
	

	@Test
	void testAddStagiaireEtallStagiaires() throws ClassNotFoundException, SQLException {
		
		
		connection = DriverManager.getConnection(URL_H2);

		// l'adresseId ne référence rien mais pas important pour le test
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "Jo@email.fr", LocalDate.now(), "123", "OTHER", 757);
		service.addStagiaire(stagiaire);
		
		stagiaire =  new Stagiaire(2,"Do", "Do@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		
		Set<Stagiaire> stagiaires = service.allStagiaires();
		assertEquals(2, stagiaires.size());
		
		
		
	}
	
	@Test
	void testRemoveStagiaire() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		// l'adresseId ne référence rien mais pas important pour le test
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "Jo@email.fr", LocalDate.now(), "123", "OTHER",458);
		service.addStagiaire(stagiaire);
		
		service.removeStagiaire(stagiaire);
		
		Set<Stagiaire> stagiaires = service.allStagiaires();
		assertEquals(0, stagiaires.size());
		
	}
	
	
	
	@Test
	void testGetStagiairesId() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		// l'adresseId ne référence rien mais pas important pour le test
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "do@email.fr", LocalDate.now(), "123", "OTHER", 757);
		service.addStagiaire(stagiaire);
		//ici c'est important car getStagiaire appelle readAdresse qui va en base adresse
		serviceAdresse.addAdresse(new Adresse(1,"nomVoie", "11111", "ville"));
		stagiaire =  new Stagiaire(2,"Do", "do@email.fr", LocalDate.now(), "456", "ADMIN", 1);
		service.addStagiaire(stagiaire);
		
		Stagiaire stagiaireGet = service.getStagiaire(2);
		assertEquals(stagiaireGet.getAge(), stagiaire.getAge());
		assertEquals(stagiaireGet.getEmail(), stagiaire.getEmail());
		assertEquals(stagiaireGet.getId(), stagiaire.getId());
		assertEquals(stagiaireGet.getRole(), stagiaire.getRole());
	}
	
	@Test
	void testGetStagiairesLoginMdp() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		// l'adresseId ne référence rien mais pas important pour le test
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "do@email.fr", LocalDate.now(), "123", "OTHER", 757);
		service.addStagiaire(stagiaire);
		//ici c'est important car getStagiaire appelle readAdresse qui va en base adresse
		serviceAdresse.addAdresse(new Adresse(1,"nomVoie", "11111", "ville"));
		stagiaire =  new Stagiaire(2,"Do", "do@email.fr", LocalDate.now(), "456", "ADMIN", 1);
		service.addStagiaire(stagiaire);
		
		Stagiaire stagiaireGet = service.getStagiaire("do@email.fr","456");
		assertEquals(stagiaireGet.getAge(), stagiaire.getAge());
		assertEquals(stagiaireGet.getEmail(), stagiaire.getEmail());
		assertEquals(stagiaireGet.getId(), stagiaire.getId());
		assertEquals(stagiaireGet.getRole(), stagiaire.getRole());
	}
	

	@Test
	void testupdateStagiaires() throws ClassNotFoundException, SQLException {
		
		
		connection = DriverManager.getConnection(URL_H2);

		Adresse adresse =new Adresse("nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse);
		Adresse adresse2 =new Adresse("nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		
		
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "jo@email.fr", LocalDate.now(), "123", "OTHER", 1);
		service.addStagiaire(stagiaire);
		serviceAdresse.addAdresse(new Adresse(9995,"nomVoie2", "22222", "ville2"));
		Stagiaire stagiaire2 =  new Stagiaire(2,"Do", "jo@email.fr", LocalDate.now(), "456", "ADMIN", 1);
		service.addStagiaire(stagiaire2);
		
		Stagiaire stagiaire1modifie = new Stagiaire(1,"Je", "je@email.fr", LocalDate.now(), "123", "OTHER", 2);
		
		service.updateStagiaire(stagiaire1modifie,adresse);
		Stagiaire stagiaireGet = service.getStagiaire(1);
		assertEquals(stagiaireGet.getAge(), stagiaire1modifie.getAge());
		assertEquals(stagiaireGet.getEmail(), stagiaire1modifie.getEmail());
		assertEquals(stagiaireGet.getId(), stagiaire1modifie.getId());
		assertEquals(stagiaireGet.getRole(), stagiaire1modifie.getRole());
		//assertEquals(stagiaireGet.getAdresse().getNomVoie(), stagiaire1modifie.getAdresse().getNomVoie()); //test des modifications d'adresse en même temps
		
		Set<Stagiaire> stagiaires = service.allStagiaires();
		assertEquals(2, stagiaires.size());
		
	}
	
	
	@Test
	void testallStagiairesAdresse() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		Adresse adresse1 =new Adresse(757,"nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse1);
		Adresse adresse2 =new Adresse(9995,"nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		Adresse adresse3 =new Adresse(7,"nomVoie3", "33333", "ville3");
		serviceAdresse.addAdresse(adresse3);
		
		
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "Jo@email.fr", LocalDate.now(), "123", "OTHER", 757);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(2,"D1", "do1@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(3,"Do2", "do2@email.fr", LocalDate.now(), "456", "OTHER", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(4,"Do3", "do3@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(5,"Do4", "do4@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(6,"Do5", "do5@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(7,"Re1", "re1@email.fr", LocalDate.now(), "789", "OTHER", 7);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(8,"Re2", "re2@email.fr", LocalDate.now(), "789", "ADMIN", 7);
		service.addStagiaire(stagiaire);
		
		Set<Stagiaire> stagiaires = service.allStagiaires(adresse2);
		assertEquals(5, stagiaires.size());
		stagiaires = service.allStagiaires(adresse3);
		assertEquals(2, stagiaires.size());
		stagiaires = service.allStagiaires(adresse1);
		assertEquals(1, stagiaires.size());
	}
	
	@Test
	void testallStagiaires() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		Adresse adresse1 =new Adresse(757,"nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse1);
		Adresse adresse2 =new Adresse(9995,"nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		Adresse adresse3 =new Adresse(7,"nomVoie3", "33333", "ville3");
		serviceAdresse.addAdresse(adresse3);
		
		
		Stagiaire stagiaire = new Stagiaire(1,"Jo", "Jo@email.fr", LocalDate.now(), "123", "OTHER", 757);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(2,"D1", "do1@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(3,"Do2", "do2@email.fr", LocalDate.now(), "456", "OTHER", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(4,"Do3", "do3@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(5,"Do4", "do4@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(6,"Do5", "do5@email.fr", LocalDate.now(), "456", "ADMIN", 9995);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(7,"Re1", "re1@email.fr", LocalDate.now(), "789", "OTHER", 7);
		service.addStagiaire(stagiaire);
		stagiaire =  new Stagiaire(8,"Re2", "re2@email.fr", LocalDate.now(), "789", "ADMIN", 7);
		service.addStagiaire(stagiaire);
		
		Set<Stagiaire> stagiaires = service.allStagiaires();
		assertEquals(8, stagiaires.size());
	}
	

	


	
	
}
