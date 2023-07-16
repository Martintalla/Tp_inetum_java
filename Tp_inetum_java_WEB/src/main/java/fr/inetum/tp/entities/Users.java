package fr.inetum.tp.entities;

import java.sql.Connection;
import java.sql.SQLException;

import fr.inetum.tp.connection.MaConnexion;

public class Users {
	private String email;
	private String mdp;
	
	public Users(String email, String mdp) {
		super();
		this.email = email;
		this.mdp = mdp;
	}

	public Users() {
		this(null, null);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
		

}
