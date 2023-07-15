package fr.inetum.formation.crud.jdbc.models;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StagiaireTest {

	private Stagiaire stagiaire;

	@BeforeEach
	void init() {
		stagiaire = new Stagiaire("Martin", "martin@inetum.fr", LocalDate.of(2001, 2, 27), "123");
	}

	@AfterEach
	void detroy() {
		stagiaire = null;
	}

	@Test
	void testGetId() {
		assertEquals(0, stagiaire.getId());
		assertTrue(0 == stagiaire.getId());
	}

	@Test
	void testSetId() {
		stagiaire.setId(5);
		assertEquals(5, stagiaire.getId());	
	}

	@Test
	void testGetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	void testSetPrenom() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testSetEmail() {
		fail("Not yet implemented");
	}

	@Test
	void testGetDdn() {
		fail("Not yet implemented");
	}

	@Test
	void testSetDdn() {
		fail("Not yet implemented");
	}

	@Test
	void testGetMdp() {
		fail("Not yet implemented");
	}

	@Test
	void testSetMdp() {
		fail("Not yet implemented");
	}

	@Test
	void testGetAge() {
		fail("Not yet implemented");
	}
}
