package fr.inetum.tp.entities;

import java.time.LocalDate;

public class Stagiaire {
	private Integer id;
	private Integer adresseId;
	private String prenom;
	private String email;
	private String mdp;
	private LocalDate ddn;
	private String role;
	private Integer age;
	
	public Stagiaire(Integer id, Integer adresseId, String prenom, String email, String mdp, LocalDate ddn,
			String role) {
		this.id = id;
		this.adresseId = adresseId;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.ddn = ddn;
		this.role = role;
	}
	
	public Stagiaire(Integer adresseId, String prenom, String email, String mdp, LocalDate ddn,
			String role) {
		this(0, adresseId, prenom, email, mdp, ddn, role);
	}

	public Stagiaire() {
		this(null, null, null, null, null, null);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAdresseId() {
		return adresseId;
	}

	public void setAdresseId(Integer adresseId) {
		this.adresseId = adresseId;
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

	public String getMdp() {
		return mdp;
	}

	public void setMdp(String mdp) {
		this.mdp = mdp;
	}

	public LocalDate getDdn() {
		return ddn;
	}

	public void setDdn(LocalDate ddn) {
		this.ddn = ddn;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}	
	
	public Integer getAge() {
		age = ddn.until(LocalDate.now()).getYears();
		return age;
	}
	

}
