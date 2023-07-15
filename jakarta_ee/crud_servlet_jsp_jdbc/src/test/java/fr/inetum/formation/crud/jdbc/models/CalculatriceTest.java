package fr.inetum.formation.crud.jdbc.models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CalculatriceTest {

	@Test
	void add() {
		Calculatrice calculatrice = new Calculatrice();
		int resulat = calculatrice.add(2, 3);
		assertEquals(4, resulat);
	}
}