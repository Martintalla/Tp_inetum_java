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

class AdresseServiceTest {

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
	void testAllAdresses() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		Adresse adresse1 =new Adresse("nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse1);
		Adresse adresse2 =new Adresse("nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		Adresse adresse3 =new Adresse("nomVoie3", "33333", "ville3");
		serviceAdresse.addAdresse(adresse3);
		
		Set<Adresse> adresses = serviceAdresse.allAdresses();
		assertEquals(3, adresses.size());

	}
	
	
	@Test
	void testReadAdresse() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);

		Adresse adresse1 =new Adresse("nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse1);
		Adresse adresse2 =new Adresse("nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		Adresse adresse3 =new Adresse("nomVoie3", "33333", "ville3");
		serviceAdresse.addAdresse(adresse3);
		
		Set<Adresse> adresses = serviceAdresse.allAdresses();
		assertEquals(3, adresses.size());
		
		
		Adresse adresseRead = serviceAdresse.readAdresse(2);
		assertEquals(adresseRead.getVille(), adresse2.getVille());
		assertEquals(adresseRead.getCodePostal(), adresse2.getCodePostal());
		
	}
	
	@Test
	void testAddAdresse() throws ClassNotFoundException, SQLException {
		connection = DriverManager.getConnection(URL_H2);
		
		Set<Adresse> adresses = serviceAdresse.allAdresses();
		assertEquals(0, adresses.size());

		Adresse adresse1 =new Adresse("nomVoie", "11111", "ville");
		serviceAdresse.addAdresse(adresse1);
		
		adresses = serviceAdresse.allAdresses();
		assertEquals(1, adresses.size());
		
		Adresse adresse2 =new Adresse("nomVoie2", "22222", "ville2");
		serviceAdresse.addAdresse(adresse2);
		
		adresses = serviceAdresse.allAdresses();
		assertEquals(2, adresses.size());
		
		Adresse adresse3 =new Adresse("nomVoie3", "33333", "ville3");
		serviceAdresse.addAdresse(adresse3);
		adresses = serviceAdresse.allAdresses();
		assertEquals(3, adresses.size());
	}

	


	
	
}
