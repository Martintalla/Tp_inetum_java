package fr.inetum.tp.filter;

import java.io.IOException;
import java.sql.SQLException;

import fr.inetum.tp.services.AdresseService;
import fr.inetum.tp.services.IAdresseService;
import fr.inetum.tp.services.IStagiaireService;
import fr.inetum.tp.services.StagiaireService;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

@WebFilter("/liste")
public class FiltreAccesListeStagiaire extends HttpFilter implements Filter {
	private IStagiaireService serviceStag;
	private IAdresseService serviceAdrs;
	
	public void init() {
		try {
			serviceStag = new StagiaireService();
			serviceAdrs = new AdresseService();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		String email = (String) request.getAttribute("email");
		String mdp = (String) request.getAttribute("mdp");
		System.out.println("je suis dans le filtre"+email+"ok");
		

		if (serviceStag.verifyCredentials(email, mdp) == true) {

			System.out.println("je suis dans le filtre");

			

		}

		chain.doFilter(request, response);

	}
}
