package tp.controleurs.filtres;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp.dao.IStagiaireService;
import tp.dao.StagiaireService;
import tp.utiles.HttpSessionCollector;

@WebFilter("/liste")
public class FiltreListe extends HttpFilter {
	private static final long serialVersionUID = 1L;
	private IStagiaireService service; //accès uniquement aux méthodes de l'interface
	private Boolean acces = false;
	private Boolean admin = false;
	
	@Override
	public void init() throws ServletException { //la méthode init est appellée une seule fois
		System.out.println("init FiltreLogin");
		service = new StagiaireService();
	}
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String ide = request.getParameter("identifiant");
		String mdpe = request.getParameter("motDePasse");
		if(ide==null) {ide="";}
		if(mdpe==null) {mdpe="";}
		
		try {
			ArrayList<Boolean> AccesEtAdmin =service.rechercherDansLaBDD(ide,mdpe);
			this.acces=AccesEtAdmin.get(0);
			this.admin=AccesEtAdmin.get(1);	
			System.out.println("this.acces="+this.acces.toString());
			System.out.println("this.admin="+this.admin.toString());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		
		try {//pour gérer le cas où la référence de la session ne mène à aucun objet.
			Boolean sessionAcces =(Boolean) HttpSessionCollector.find(request.getParameter("sessionId")).getAttribute("acces");
			Boolean sessionAdmin =(Boolean) HttpSessionCollector.find(request.getParameter("sessionId")).getAttribute("admin");
			if(sessionAcces!=null) {this.acces = sessionAcces;}
			if(sessionAdmin!=null) {this.admin = sessionAdmin;}
		} catch (NullPointerException e) {
			
		}
		try {
			Boolean requeteAcces =(Boolean) request.getAttribute("admin");
			Boolean requeteAdmin =(Boolean) request.getAttribute("acces");
			if(requeteAcces!=null) {this.acces = requeteAcces;}
			if(requeteAdmin!=null) {this.admin = requeteAdmin;}
		} catch (NullPointerException e) {
			
		}
		
		System.out.println("this.acces="+this.acces.toString());
		System.out.println("this.admin="+this.admin.toString());
		
		if(this.acces) {
			System.out.println("Le Filtre laisse passer");
			
			//on crée la session pour le reste de l'usage du site
			
			HttpSession session = request.getSession(); 
			String sessionId= session.getId();
			session.setAttribute("acces", true);
			session.setAttribute("admin", this.admin);//donner l'information si l'utilisateur est administrateur ou non.
		
			request.setAttribute("admin", this.admin);//donner l'information si l'utilisateur est administrateur ou non.
			request.setAttribute("sessionId", sessionId);
			
			chain.doFilter(request, response); //on passe l'information à la servlet Liste
		}
		else {
			System.out.println("Le Filtre bloque");
			response.sendRedirect("login?echec=1"); //pour rediriger vers la servlet
		}
	}	
}