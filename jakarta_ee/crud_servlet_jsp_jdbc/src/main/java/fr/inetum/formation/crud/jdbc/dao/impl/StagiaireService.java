package fr.inetum.formation.crud.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import fr.inetum.formation.crud.jdbc.dao.IStagiaireService;
import fr.inetum.formation.crud.jdbc.models.Stagiaire;
import fr.inetum.formation.crud.jdbc.utils.AppUtil;
import fr.inetum.formation.jdbc.utils.MaConnexion;

public class StagiaireService implements IStagiaireService{

	private Connection connection;
	
	public StagiaireService(Connection connection) {
		this.connection = connection;
	}

	public StagiaireService() throws ClassNotFoundException, SQLException {
		connection = MaConnexion.getInstance().getConnection();
	}

	@Override
	public List<Stagiaire> allStagiaires() throws ClassNotFoundException, SQLException {
		List<Stagiaire> stagiaires = new ArrayList<>();
		
		String requete = "SELECT * FROM Stagiaire";
		//Connection connection = MaConnexion.getInstance().getConnection();
		PreparedStatement stmt = connection.prepareStatement(requete);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String prenom = rs.getString("prenom");
			String email = rs.getString("email");
			String mdp = rs.getString("mdp");
			LocalDate ddn = rs.getDate("ddn").toLocalDate();
			Stagiaire stagiaire = new Stagiaire(id, prenom, email, ddn, mdp);
			stagiaires.add(stagiaire);
		}
		return stagiaires;
	}

	@Override
	public void addStagiaire(Stagiaire stagiaire) throws ClassNotFoundException, SQLException {
		String requete = "INSERT INTO Stagiaire(prenom, email, mdp, ddn) VALUES (?, ?, ?, ?)";
		//Connection connection = MaConnexion.getInstance().getConnection();
		PreparedStatement stmt = connection.prepareStatement(requete);
		
		stmt.setString(1, AppUtil.capitalize(stagiaire.getPrenom()));
		stmt.setString(2, stagiaire.getEmail().toLowerCase());
		stmt.setString(3, AppUtil.hashPassword(stagiaire.getMdp()));
		stmt.setDate(4, Date.valueOf(stagiaire.getDdn()));
		
		stmt.executeUpdate();
	}

	@Override
	public void deleteStagiaire(int id) throws ClassNotFoundException, SQLException {
		String requete = "DELETE FROM Stagiaire WHERE id = ?";
		//Connection connection = MaConnexion.getInstance().getConnection();
		PreparedStatement stmt = connection.prepareStatement(requete);
		
		stmt.setInt(1, id);
		stmt.executeUpdate();
	}
}
