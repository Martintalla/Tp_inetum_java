package tp.dao;

import java.sql.SQLException;
import java.util.Set;

import tp.modeles.Adresse;

public interface IAdresseService {

	Adresse readAdresse(int idAdresse) throws ClassNotFoundException, SQLException;

	Set<Adresse> allAdresses() throws ClassNotFoundException, SQLException;

	void addAdresse(Adresse adresse) throws ClassNotFoundException, SQLException;

	int dernierIdTableAdresse() throws ClassNotFoundException, SQLException;

	void modifierAdresse(Adresse adresse) throws ClassNotFoundException, SQLException;

}
