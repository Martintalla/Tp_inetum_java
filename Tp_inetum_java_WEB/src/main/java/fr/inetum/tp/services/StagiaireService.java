package fr.inetum.tp.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import fr.inetum.tp.connection.MaConnexion;
import fr.inetum.tp.entities.Adresse;
import fr.inetum.tp.entities.Stagiaire;
import fr.inetum.tp.utils.AppUtil;
import jakarta.servlet.ServletException;

public class StagiaireService implements IStagiaireService {
	
	private Connection connection;	
	public String admin;
	public String user;
	public String error;
	//Connexion à la BDD
	
	public StagiaireService() throws ClassNotFoundException, SQLException {
			connection = MaConnexion.getInstance().getConnection();
		}
	
	public StagiaireService(Connection connection, IAdresseService iadresseService){
		
	}
	//Constr
	public StagiaireService(Connection connection) {
		this.connection = connection;
	}
	
	//IAdresseService serviceAdresse = new AdresseService();
    // Liste de tous les stagiaires
	@Override
	public Set<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException {
        Set<Stagiaire> stagiaires = new HashSet<>();
		
		String requete = "SELECT * FROM Stagiaire";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseId");
			Stagiaire stagiaire = new Stagiaire(id, adresseId, prenom, email, mdp, ddn, role);
			stagiaires.add(stagiaire);
		}
		
		return stagiaires;
	}

	//Liste des stagiaires résidant à l’adresse passée en argument.
	
	@Override
	public Set<Stagiaire> allStagiaires(Adresse adresse) throws ClassNotFoundException, SQLException {
		Set<Stagiaire> stagiaires = new HashSet<Stagiaire>();
		
		String requete = "SELECT * FROM Stagiaire WHERE adresseId = ? ";
		
		PreparedStatement stmt = connection.prepareStatement(requete);
		stmt.setInt(1, adresse.getId());
		
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			String role =  rs.getString("role");
			Integer adresseId =  rs.getInt("adresseId");
			Stagiaire stagiaire = new Stagiaire(id, adresseId, prenom, email, mdp, ddn, role);
			stagiaires.add(stagiaire);
		}
		return stagiaires;
	}

	//Supprimer un stagiaire de la base de données
	
	@Override
	public void removeStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		String requete = "DELETE FROM Stagiaire WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(requete);
		stmt.setInt(1, stagiaire.getId());
		stmt.executeUpdate();
				

	}
	
	//Supprimer un Stagiaire par id
	
	public void removeStagiaire(int id) throws SQLException  {
		String requete = "DELETE FROM Stagiaire WHERE id = ?";
		PreparedStatement stmt = connection.prepareStatement(requete);
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}

	@Override
	public void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public Stagiaire getStagiaire(String email, String mdp) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stagiaire getStagiaire(Integer id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Stagiaire UpdateStagiaire(Stagiaire stagiaire) throws SQLException {
		String requete = "UPDATE Stagiaire SET prenom =?, email=?, mdp=?, ddn = ?, role=? WHERE id = ?";
		
		
		PreparedStatement stmt = connection.prepareStatement(requete);
		
		stmt.setString(1, AppUtil.capitalize(stagiaire.getPrenom()));
		stmt.setString(2, stagiaire.getEmail().toLowerCase());
		stmt.setString(3, AppUtil.hashPassword(stagiaire.getMdp()));
		stmt.setDate(4, Date.valueOf(stagiaire.getDdn()));
		stmt.setString(5, stagiaire.getRole());
		stmt.setInt(6, stagiaire.getId());
		stmt.executeUpdate();
		
		return stagiaire;
			
		
	}
	
	public boolean verifyCredentials(String email, String mdp) {
		try {
			String requete = "SELECT * FROM Stagiaire WHERE email=? AND mdp= ?";
			PreparedStatement stmt = connection.prepareStatement(requete); 
			
			stmt.setString(1, email);
			stmt.setString(2, mdp);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
			//Les identifiants sont valides
				return true;

}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Les identifiants sont invalides
		return false;
 }
}
