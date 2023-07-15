package tp.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import tp.modeles.Adresse;
import tp.utiles.AppUtils;
import tp.utiles.ConnexionSingleton;

public class AdresseService implements IAdresseService {
	
private Connection connection;
	
	public AdresseService(Connection connection) {
		this.connection = connection;
	}

	public AdresseService() {
		try {
			connection = ConnexionSingleton.getInstanceSingleton().getConnectionAssociee();
		}catch(Exception e) {
			
		}
	}
	
	@Override
	public Set<Adresse> allAdresses() throws ClassNotFoundException, SQLException{
		Set<Adresse> adresses = new HashSet<>();
		
		String requete = "SELECT * FROM table_tp_adresse";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		ResultSet rs = stmt.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String nomVoie = rs.getString("nomvoie");
			String codePostal = rs.getString("codepostal");
			String ville = rs.getString("ville");
			Adresse adresse = new Adresse(id,nomVoie, codePostal, ville);
			adresses.add(adresse);
		}
		
		return adresses;
	}
	
	@Override
	public Adresse readAdresse(int idAdresse) throws ClassNotFoundException, SQLException{
		Adresse adresse = null;
		String REQUETE = String.format("SELECT * FROM table_tp_adresse WHERE id='%s'",idAdresse);
		PreparedStatement stm = this.connection.prepareStatement(REQUETE);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			int id = rs.getInt("id");
			String nomVoie = rs.getString("nomvoie");
			String codePostal = rs.getString("codepostal");
			String ville = rs.getString("ville");
			adresse = new Adresse(id,nomVoie, codePostal, ville);
		}
		
		return adresse;
	}
	
	public void completeAdresse(Adresse adresseNonRenseignee) throws ClassNotFoundException, SQLException {	
		Adresse adresseCompletee = readAdresse(adresseNonRenseignee.getId());	
		adresseNonRenseignee.setNomVoie(adresseCompletee.getNomVoie());
		adresseNonRenseignee.setCodePostal(adresseCompletee.getCodePostal());
		adresseNonRenseignee.setVille(adresseCompletee.getVille());
	}
	
	@Override
	public void addAdresse(Adresse adresse) throws ClassNotFoundException, SQLException {	
		String requete = "INSERT INTO table_tp_adresse (nomvoie,codepostal,ville) VALUES (?,?,?)";
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		
		System.out.println("nomvoie, codepostal, ville : "+adresse.getNomVoie()+", "+adresse.getCodePostal()+", "+adresse.getVille());
		
		stmt.setString(1,adresse.getNomVoie());
		stmt.setString(2,adresse.getCodePostal());
		stmt.setString(3,adresse.getVille());
		
		stmt.executeUpdate();
		
		System.out.println("sysout de addAdresse");
	}

	@Override
	public int dernierIdTableAdresse() throws ClassNotFoundException, SQLException {
		int id=0;
		String requete = "SELECT * FROM table_tp_adresse ORDER BY id";
		PreparedStatement stm = this.connection.prepareStatement(requete);
		ResultSet rs = stm.executeQuery();
		
		while(rs.next()) {
			id = rs.getInt("id");
			System.out.println("dernierIdTableAdresse, id="+id);
		}
		
		
		
		return id;
	}
	
	@Override
	public void modifierAdresse(Adresse adresse) throws ClassNotFoundException, SQLException {	
		
		System.out.println("sysout de modifierAdresse");
		String requete = "UPDATE table_tp_adresse SET nomvoie =?, codepostal =?, ville=? WHERE id=?";
		
		PreparedStatement stmt = this.connection.prepareStatement(requete);
		

		stmt.setString(1,adresse.getNomVoie());
		stmt.setString(2,adresse.getCodePostal());
		stmt.setString(3,adresse.getVille());
		stmt.setInt(4, adresse.getId());
		
		stmt.executeUpdate();
	}
	

}
