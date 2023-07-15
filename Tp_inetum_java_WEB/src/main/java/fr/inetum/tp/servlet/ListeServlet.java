package fr.inetum.tp.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import fr.inetum.tp.entities.Adresse;
import fr.inetum.tp.entities.Stagiaire;
import fr.inetum.tp.services.AdresseService;
import fr.inetum.tp.services.IAdresseService;
import fr.inetum.tp.services.IStagiaireService;
import fr.inetum.tp.services.StagiaireService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/liste")
public class ListeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/pages/liste.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			int id = Integer.parseInt(request.getParameter("id"));
			
			String action = request.getParameter("action");
			
			if (id !=0 && action.equalsIgnoreCase("delete")) {
				serviceStag.removeStagiaire(id);	
			}
			
			Set<Stagiaire> stagiaires = new HashSet<>();
			 stagiaires = serviceStag.allStagiaires();
			 
			 Set<Adresse> adresses = new HashSet<Adresse>();
			 adresses = serviceAdrs.allAdresses();
			 
			 request.setAttribute("stagiaires", stagiaires);
			 request.setAttribute(action, action);
		} catch (NumberFormatException | ClassNotFoundException | SQLException e) {
			
			List<String> erreurs = new ArrayList<String>();
			
			if (e instanceof ClassNotFoundException) {
				erreurs.add("attention !! les drivrers semblent introuvables");
				}
			if (e instanceof CommunicationsException) {
				erreurs.add("Attention !! le serveur de base de données semble à l'arrêt");
			}
			request.setAttribute("erreurs", erreurs);
			}
		doGet(request, response);
		 
	}

}
