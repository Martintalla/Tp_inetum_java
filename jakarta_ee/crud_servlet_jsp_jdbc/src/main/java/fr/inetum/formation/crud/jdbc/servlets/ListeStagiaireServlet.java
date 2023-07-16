package fr.inetum.formation.crud.jdbc.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import fr.inetum.formation.crud.jdbc.dao.IStagiaireService;
import fr.inetum.formation.crud.jdbc.dao.impl.StagiaireService;
import fr.inetum.formation.crud.jdbc.models.Stagiaire;

@WebServlet("/liste")
public class ListeStagiaireServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private IStagiaireService service;

	@Override
	public void init() throws ServletException {
		try {
			service = new StagiaireService();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");

		try {
			if (id != null && Integer.parseInt(id) != 0) {
				service.deleteStagiaire(Integer.parseInt(id));
			}			
			List<Stagiaire> stagiaires = new ArrayList<>();
			stagiaires = service.allStagiaires();
				System.out.println("id"+id);
			request.setAttribute("stagiaires", stagiaires);
		} catch (ClassNotFoundException | SQLException e) {
			List<String> erreurs = new ArrayList<>();
			
			if(e instanceof ClassNotFoundException) {
				erreurs.add("Attention, les drivers de la BDD sont introuvables");
			}
			
			if(e instanceof CommunicationsException) {
				erreurs.add("Le serveur de BDD semble à l'arrêt");
			}
			request.setAttribute("erreurs", erreurs);
		}
		
			
		request.getRequestDispatcher("/WEB-INF/jsp/liste.jsp").forward(request, response);
	}
}