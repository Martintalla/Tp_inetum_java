package tp.controlleurs;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp.dao.IAdresseService;
import tp.dao.IStagiaireService;
import tp.dao.StagiaireService;
import tp.modeles.Stagiaire;

@WebServlet("/liste")
public class ListeStagiaireServlet extends HttpServlet {


	private static final long serialVersionUID = 1L;
	private IStagiaireService service; //accès uniquement aux méthodes de l'interface
	private IAdresseService serviceAdresse;
	private Boolean admin=false;

	
	@Override
	public void init() throws ServletException { //la méthode init est appellée une seule fois
		System.out.println("init ListeStagiaireServlet");
		service = new StagiaireService();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.admin=(Boolean) request.getAttribute("admin");
		
		//gestion des paramètres pour la jsp
		request.setAttribute("suppressionStagiaire", request.getParameter("suppressionStagiaire"));
		request.setAttribute("champsFormulaireNull", request.getParameter("champsFormulaireNull"));
		request.setAttribute("validationFormulaire", request.getParameter("validationFormulaire"));
		
		

		
		if(request.getParameter("numdelid")!=null) {
			Integer id = Integer.parseInt(request.getParameter("numdelid"));
			System.out.println("numdelid = "+id);
			doDelete(id, request, response);
		}
		else {
			System.out.println("doGet ListeStagiaireServlet");
			List<Stagiaire> stagiaires = new ArrayList<>();
			try {
				stagiaires = service.tousStagiaires();
				request.setAttribute("stagiaires", stagiaires);
				//adresseId etc.
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			
			//request.setAttribute("ThisAdmin", this.admin);
			
			request.getRequestDispatcher("/WEB-INF/pages/liste.jsp").forward(request, response); //ici la VUE
		}
	}
	
	
	protected void doDelete(Integer id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doDelete ListeStagiaireServlet");
		try {
			service.deleteStagiaire(id);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		HttpSession session = request.getSession();
		String sessionId= session.getId();
		session.setAttribute("acces", true);
		session.setAttribute("admin", true);
		response.sendRedirect("liste?sessionId="+sessionId+"&suppressionStagiaire=1");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req,resp);
	}
	
	
	
}
