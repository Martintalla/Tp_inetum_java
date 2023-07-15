package tp.controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp.dao.AdresseService;
import tp.dao.IAdresseService;
import tp.dao.IStagiaireService;
import tp.dao.StagiaireService;
import tp.modeles.Adresse;
import tp.modeles.Stagiaire;
import tp.utiles.HttpSessionCollector;

/**
 * Servlet implementation class LoadingServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
       

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet LoginServlet");
		
		//gestion des paramètres pour la jsp
		request.setAttribute("echec", request.getParameter("echec"));
		
		
		IStagiaireService serviceStagiaire = new StagiaireService();
		try {
			if(!serviceStagiaire.checkIlExisteAuMoinsUnAdmin()) {
				creerAdminParDefaut();
			}
		} catch (ClassNotFoundException | SQLException | ServletException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		//Récupère la session créee par le filtreListe de sorte à pouvoir y accéder (à l'id) depuis la Vue login.jsp
		String sessionId=(String) request.getAttribute("sessionId");
		request.setAttribute("sessionId", sessionId);
		
		request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
		
		
		if(request.getParameter("deconnexion") != null) { //lors d'une déconnexion on détruit les informations de la session précédente :
			HttpSession session = HttpSessionCollector.find(request.getParameter("sessionId"));
			session.removeAttribute("acces");
			session.removeAttribute("admin");
			request.removeAttribute("acces");
			request.removeAttribute("admin");
			request.removeAttribute("sessionId");
			
			HttpSession session2 =request.getSession();
			session2.removeAttribute("acces");
			session2.removeAttribute("admin");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost LoginServlet");
	}
	
	
	
	public void creerAdminParDefaut() throws ServletException { //vérifier que le compte admin par défaut existe toujours
		IAdresseService serviceAdresse = new AdresseService();
		IStagiaireService serviceStagiaire = new StagiaireService();
		
		Adresse adresse = new Adresse("VoieAdmin","0000","VilleAdmin");
		
		int i = 0;
		try {
			serviceAdresse.addAdresse(adresse);
			i = serviceAdresse.dernierIdTableAdresse();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		adresse.setId(i);

	
		Stagiaire stagiaire = new Stagiaire("Admin", "admin@admin.admin", LocalDate.parse("0001-01-01"), "admin", "ADMIN", adresse);

		try {
			serviceStagiaire.addStagiaire(stagiaire);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		super.init();
	}


}
