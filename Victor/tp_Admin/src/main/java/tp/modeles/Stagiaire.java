package tp.modeles;

import java.sql.SQLException;
import java.time.LocalDate;

import tp.dao.AdresseService;
import tp.dao.IAdresseService;

public class Stagiaire {
	
	private Integer id;
	private String prenom;
	private String mdp;
	private String email;
	private Adresse adresse;
	private LocalDate ddn;
	private String role;
	private Integer age=0; //on va le calculer.
	
	public Stagiaire(Integer id, String prenom, String mdp, String email, LocalDate ddn, String role, Integer adresseId) {
		this.id = id;
		this.prenom = prenom;
		this.mdp = mdp;
		this.email = email;
		this.ddn = ddn;
		this.role= role;
		this.adresse= new Adresse(adresseId);
	}
	
	public Stagiaire(Integer id, String prenom, String email, LocalDate ddn, String mdp,  String role, Adresse adresse) {
		this.id = id;
		this.prenom = prenom;
		this.mdp = mdp;
		this.email = email;
		this.ddn = ddn;
		this.role= role;
		this.adresse= adresse;
	}
	
	//constructeur avec permutation
	//(id,prenom, email, ddn, mdp, role)
	public Stagiaire(Integer id, String prenom, String email, LocalDate ddn, String mdp,  String role, Integer adresseId) {
		this(id, prenom, mdp, email, ddn, role, adresseId);	
	}
	
	public Stagiaire(String prenom, String email, LocalDate ddn, String mdp,  String role, Adresse adresse) {
		this(0, prenom, email, ddn, mdp,  role, adresse);
	}
	
	
	
	/*
	public Stagiaire() {
		this(1, null, null, null, null, null);
	}
	*/

	public Adresse getAdresse() {
		return this.adresse;
	}

	public void setAdresse(Adresse adresse) {
		this.adresse = adresse;
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
	
	public String getMotDePasse() {
		return mdp;
	}
	
	public String getMdp() {
		return this.getMotDePasse();
	}

	public void setMotDePasse(String mdp) {
		this.mdp = mdp;
	}
	
	public void setMdp(String mdp) {
		this.setMotDePasse(mdp);
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
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public int getAge() {
		return ddn.until(LocalDate.now()).getYears();
	}
	
	/*
	public Adresse getAccesAdresse() {
		return service.readAdresse(this.adresseId);
	}
	*/

	@Override
	public String toString() {
		return "Stagiaire [id=" + id + ", prenom=" + prenom + ", mdp=***, email=" + email
				+ ", ddn=" + ddn + ", role="+role+"]";
	}


	
	
}
