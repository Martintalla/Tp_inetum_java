package tp.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import tp.modeles.Adresse;
import tp.modeles.Stagiaire;
import tp.utiles.AppUtils;
import tp.utiles.ConnexionSingleton;



public class StagiaireService implements IStagiaireService {
	
	private Connection connection;
	
	public StagiaireService(Connection connection) {
		this.connection = connection;
	}

	public StagiaireService() {
		try {
			connection = ConnexionSingleton.getInstanceSingleton().getConnectionAssociee();
		}catch(Exception e) {
			
		}
	}
	
	private IAdresseService serviceAdresse = new AdresseService();

	@Override
	public List<Stagiaire> tousStagiaires() throws ClassNotFoundException, SQLException{
		List<Stagiaire> stagiaires = new ArrayList<>();
		
		String requete = "SELECT * FROM table_tp_admin";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseid");
			Stagiaire stagiaire = new Stagiaire(id,prenom, email, ddn, mdp, role, serviceAdresse.readAdresse(adresseId));
			stagiaires.add(stagiaire);
		}
		
		return stagiaires;
	}

	@Override
	public void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		String requete = "INSERT INTO table_tp_admin (prenom, email, mdp, ddn, role, adresseid) VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		
		stmt.setString(1, AppUtils.capitalizeWord(stagiaire.getPrenom()));
		stmt.setString(2, stagiaire.getEmail().toLowerCase());
		stmt.setString(3, AppUtils.hashPassword(stagiaire.getMdp()));
		stmt.setDate(4, Date.valueOf(stagiaire.getDdn()));
		stmt.setString(5, AppUtils.capitalizeWord(stagiaire.getRole()));
		stmt.setInt(6, stagiaire.getAdresse().getId());
		
		stmt.executeUpdate();
		
		System.out.println("sysout de addStagiaire");
		
	}
	
	@Override
	public void deleteStagiaire(Integer id) throws ClassNotFoundException, SQLException {
		System.out.println("sysout de deleteStagiaire "+id);
		String requete = "DELETE FROM table_tp_admin WHERE id="+id;
		
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		
		stmt.executeUpdate();
		
		System.out.println("sysout de deleteStagiaire");
		
	}
	
	@Override
	public void modifierStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		System.out.println("sysout de modifierStagiaire");
		String requete = "UPDATE table_tp_admin SET prenom =?, email =?, mdp=?, ddn =?, role=?  WHERE id=?"; //on ne va pas modifier l'adresseId a priori
		
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		
		stmt.setString(1, AppUtils.capitalizeWord(stagiaire.getPrenom()));
		stmt.setString(2, stagiaire.getEmail().toLowerCase());
		stmt.setString(3, AppUtils.hashPassword(stagiaire.getMdp()));
		stmt.setDate(4, Date.valueOf(stagiaire.getDdn()));
		stmt.setString(5, AppUtils.capitalizeWord(stagiaire.getRole()));
		stmt.setInt(6, stagiaire.getId());
		
		
		stmt.executeUpdate();
	}
	

	@Override
	public ArrayList<Boolean>  rechercherDansLaBDD(String ideEmail, String mdpe) throws ClassNotFoundException, SQLException {
		ArrayList<Boolean> AccesEtAdmin = new ArrayList<>();
		boolean acces=false;
		boolean admin=false;
		
		String requete = String.format("SELECT * FROM table_tp_admin WHERE email='%s'",ideEmail);
		
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		int i = 0;
		int id=0;
		String mdp="";
		while(rs.next()) {
			i++;
			id = rs.getInt("id");
			mdp = rs.getString("mdp");
			String role = rs.getString("role");
			System.out.println("Le filtre a trouvé la personne dans la BDD, il s'agit de l'id n°"+id);
			admin=role.equals("ADMIN");
		}
		if(i>0 && AppUtils.checkPassword(mdpe, mdp)) {acces=true;} else {acces=false;}
		if(!acces) { System.out.println("le filtre n'a trouvé aucune personne correspondante dans la BDD"); };
		
		AccesEtAdmin.add(acces);
		AccesEtAdmin.add(admin);
		return AccesEtAdmin; 
	}
	
	@Override
	public int dernierIdTableStagiaire() throws ClassNotFoundException, SQLException {
		int id=0;
		String requete = "SELECT MAX(id) FROM table_tp_admin";
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			id = rs.getInt("id");
		}
		
		return id;
	}
	
	@Override
	public int adresseIddelIdStagiaire(int idStagiaire) throws ClassNotFoundException, SQLException {
		int adresseId=0;
		String requete = String.format("SELECT adresseid FROM table_tp_admin WHERE id='%s'",idStagiaire);
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			adresseId = rs.getInt("adresseid");
		}
		
		return adresseId;
	}
	
	@Override
	public Boolean checkIlExisteAuMoinsUnAdmin() throws ClassNotFoundException, SQLException {
		Boolean existe=false;
		
		String requete = String.format("SELECT * FROM table_tp_admin WHERE role='ADMIN' ");
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			existe=true;
		}
		
		return existe;
	}
	
	
	/*
	 * 
	 * 
	 * Ici les méthodes demandées dans l'énoncé :
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
	@Override
	public Set<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException{
		Set<Stagiaire> stagiaires = new HashSet<>();
		
		String requete = "SELECT * FROM table_tp_admin";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseid");
			Stagiaire stagiaire = new Stagiaire(id,prenom, email, ddn, mdp, role, serviceAdresse.readAdresse(adresseId));
			stagiaires.add(stagiaire);
		}
		
		return stagiaires;
	}
	
	@Override
	public Set<Stagiaire> allStagiaires(Adresse adresse) throws ClassNotFoundException, SQLException{
		Set<Stagiaire> stagiaires = new HashSet<>();
		
		String requete = String.format("SELECT * FROM table_tp_admin WHERE adresseid='%s'",adresse.getId());
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseid");
			Stagiaire stagiaire = new Stagiaire(id,prenom, email, ddn, mdp, role, serviceAdresse.readAdresse(adresseId));
			stagiaires.add(stagiaire);
		}
		
		return stagiaires;
	}
	
	@Override
	public Stagiaire getStagiaire(int id) throws ClassNotFoundException, SQLException {
		Stagiaire stagiaire = null;
		
		String requete = String.format("SELECT * FROM table_tp_admin WHERE id='%s'",id);
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseid");
			stagiaire = new Stagiaire(id,prenom, email, ddn, mdp, role, serviceAdresse.readAdresse(adresseId));

		}
		
		return stagiaire;
	}
	
	@Override
	public void addStagiaire(Stagiaire stagiaire, Adresse adresse) throws ClassNotFoundException, SQLException {
		addStagiaire(stagiaire);
		
	}
	
	@Override
	public void updateStagiaire(Stagiaire stagiaire, Adresse adresse) throws ClassNotFoundException, SQLException {
		modifierStagiaire(stagiaire);
	}
	
	@Override
	public void removeStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		deleteStagiaire(stagiaire.getId());
	}
	
	@Override
	public Stagiaire getStagiaire(String login, String mdp) throws ClassNotFoundException, SQLException {

		
		String requete = String.format("SELECT * FROM table_tp_admin WHERE email='%s'",login);
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		int i = 0;
		int id=0;
		String mdpBDD="";
		while(rs.next()) {
			i++;
			id = rs.getInt("id");
			mdpBDD = rs.getString("mdp");
		}
		if(i>0 && AppUtils.checkPassword(mdp, mdpBDD)) {
			return getStagiaire(id);
		} 
		else {
			return null;
		}
		
	}

}
