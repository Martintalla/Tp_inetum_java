package fr.inetum.filters;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/maserv")
public class MonFiltre implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Je suis de le filtre MonFiltre");
		if(true) {
			// TOUT EST OK POUR LAISSER PASSER LA REQUETE
			chain.doFilter(request, response);
		}		
	}
}
