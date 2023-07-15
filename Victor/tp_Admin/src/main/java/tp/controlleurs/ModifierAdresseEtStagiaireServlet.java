package tp.controlleurs;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

@WebServlet("/modifier")
public class ModifierAdresseEtStagiaireServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private IStagiaireService service;
	private IAdresseService serviceAdresse;
	
	@Override
	public void init() throws ServletException { //la méthode init est appellée une seule fois
		System.out.println("init ModifierAdresseEtStagiaireServlet");
		service = new StagiaireService();
		serviceAdresse = new AdresseService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/pages/ajouter.jsp").forward(req, resp);
	}

	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
		HttpSession session = req.getSession();
		String sessionId= session.getId();
		session.setAttribute("acces", true);
		session.setAttribute("admin", true);
		
	
		
		//Adresse dans le formulaire :
		String nomvoie= req.getParameter("nomvoie");
		String codepostal= req.getParameter("codepostal");
		String ville= req.getParameter("ville");
		
		//Stagiaire dans le formulaire :
		Integer id = Integer.parseInt(req.getParameter("nummodid"));
		System.out.println("doPost ModifierStagiaireServlet id="+id);
		String prenom= req.getParameter("prenom");
		String email= req.getParameter("email");
		String mdp= req.getParameter("mdp");
		LocalDate ddn=null;
		try {
			ddn= LocalDate.parse(req.getParameter("ddn"));
		} catch(DateTimeParseException e){
			
		}
		String role =  req.getParameter("role");
		
		if(nomvoie=="" || codepostal=="" || ville=="" || prenom=="" || email=="" || mdp=="" || ddn==null || role=="") {
			resp.sendRedirect("liste?sessionId="+sessionId+"&champsFormulaireNull=2");	
		}
		else {
			
			System.out.println("nomvoie, codepostal, ville : "+nomvoie+" "+codepostal+" "+ville);
			
			Adresse adresse = new Adresse(nomvoie,codepostal,ville); //on retrouvera l'id de l'adresse après avoir trouvé la personne
			
			int i = 0;
			try {
				adresse.setId(service.adresseIddelIdStagiaire(id));
				serviceAdresse.modifierAdresse(adresse);
				i = serviceAdresse.dernierIdTableAdresse();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
					
		
			Stagiaire stagiaire = new Stagiaire(id,prenom, email, ddn, mdp, role, adresse);
			
			try {
				service.modifierStagiaire(stagiaire);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			resp.sendRedirect("liste?sessionId="+sessionId+"&validationFormulaire=2");
		
		}
		
	}
	
	
}
