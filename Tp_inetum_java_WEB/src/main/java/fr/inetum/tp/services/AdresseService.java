package fr.inetum.tp.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import com.mysql.cj.xdevapi.Statement;

import fr.inetum.tp.connection.MaConnexion;
import fr.inetum.tp.entities.Adresse;


public class AdresseService implements IAdresseService{
private Connection connection;
	
	public AdresseService(Connection connection) {
		this.connection = connection;
	}

	public AdresseService() throws ClassNotFoundException, SQLException  {
		
			
				connection = MaConnexion.getInstance().getConnection();
			
		
	}
//Affiche la liste de toutes les adresses
	@Override
	public Set<Adresse> allAdresses() throws ClassNotFoundException, SQLException {
		Set<Adresse> adresses = new HashSet<Adresse>();
		String requete = "SELECT * FROM Adresse";
		PreparedStatement stmt = connection.prepareStatement(requete);
		
		ResultSet rs = stmt.executeQuery();
		
		while (rs.next()) {
			
			int id = rs.getInt("id");
			String nomVoie = rs.getString("nomVoie");
			String codePostal = rs.getString("codePostal");
			String ville  = rs.getString("ville");
			
			Adresse adresse = new Adresse(id, nomVoie, codePostal, ville);
			adresses.add(adresse);
		}
		return adresses;
	}
 //Rechercher par id
	@Override
	public Adresse readAdresses(int id) throws ClassNotFoundException, SQLException {
		Adresse adresse = null;
		String requete = ("SELECT * FROM Adresse WHERE id=?");
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		if (rs.next()) {
			adresse = new Adresse(
					rs.getInt(id),
					rs.getString("nomVoie"),
					rs.getString("codePostal"), 
					rs.getString("ville"));
		}
		
		return adresse;
	}
//PERSISTER UNE ADRESSE EN BASE DE DONNEES
	@Override
	public Adresse addAdresse(Adresse adresse) throws ClassNotFoundException, SQLException {
		 int id = 0;
		String requete = "INSERT INTO Adresse (nomVoie, codePostal, ville) VALUES (?,?,?)";
		PreparedStatement stmt = connection.prepareStatement(requete);
		stmt.setString(1, adresse.getNomVoie());
		stmt.setString(2, adresse.getCodePostal());
		stmt.setString(3, adresse.getVille());
		stmt.executeUpdate();
		ResultSet rs = stmt.getGeneratedKeys();
		if (rs.next()) {
			id = rs.getInt(1);
			
			adresse.setId(id);
			
		}
		return adresse;
		
	}

	@Override
	public int dernierIdTableAdresse() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void modifierAdresse(Adresse adresse) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

}
