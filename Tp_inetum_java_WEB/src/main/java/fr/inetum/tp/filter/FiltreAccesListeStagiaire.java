package fr.inetum.tp.filter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.inetum.tp.connection.MaConnexion;

@WebFilter("/lis")
public class FiltreAccesListeStagiaire extends HttpFilter implements Filter {
private Connection connection;
	
	public FiltreAccesListeStagiaire(Connection connection) {
		this.connection = connection;
	}

	public FiltreAccesListeStagiaire() throws ClassNotFoundException, SQLException  {
		
			
				connection = MaConnexion.getInstance().getConnection();
	}			
       
   
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		try {
			String email = request.getParameter("email");
			String mdp = request.getParameter("mdp");
			
			String requete = "SELECT * FROM Stagiaire WHERE email=? AND mdp= ?";
			PreparedStatement stmt = connection.prepareStatement(requete); 
			
			stmt.setString(1, email);
			stmt.setString(2, mdp);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				
				String role = rs.getString("role");
				if (role.equals("ADMIN")) {
					// L'utilsateur est administrateur
					System.out.println("l'utilisateur"+email+"est adminisatrateur");
					chain.doFilter(request, response);
					
				}else if (role.equals("USER")) {
					//l'utilisateur est un utilisateur standard
					System.out.println("l'utilisateur"+email+"est un utilsateur standard");
					chain.doFilter(request, response);
				}
				
			}
		} catch (SQLException | IOException | ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //else {
//				request.setAttribute("unknownUser", "email et/ou mot de passe incorrect");
//			
//		}
//		
//
//		// pass the request along the filter chain
//		chain.doFilter(request, response);
	}

	

}
