package fr.inetum.formation.crud.jdbc.models;

import java.time.LocalDate;

public class Stagiaire {

	private Integer id;
	private String prenom;
	private String email;
	private LocalDate ddn;
	private String mdp;
	private Integer age;

	public Stagiaire(Integer id, String prenom, String email, LocalDate ddn, String mdp) {
		this.id = id;
		this.prenom = prenom;
		this.email = email;
		this.ddn = ddn;
		this.mdp = mdp;
	}

	public Stagiaire(String prenom, String email, LocalDate ddn, String mdp) {
		this(0, prenom, email, ddn, mdp);
	}
	
	public Stagiaire() {
		this(null, null, null, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDdn() {
		return ddn;
	}

	public void setDdn(LocalDate ddn) {
		this.ddn = ddn;
	}

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	public Integer getAge() {
		age = ddn.until(LocalDate.now()).getYears();
		return age;
	}
}
