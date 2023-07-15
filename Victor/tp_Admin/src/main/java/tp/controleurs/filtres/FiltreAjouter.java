package tp.controleurs.filtres;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import tp.utiles.HttpSessionCollector;

@WebFilter("/ajouter")
public class FiltreAjouter extends HttpFilter {
	private static final long serialVersionUID = 1L;
	private Boolean acces = false;
	private Boolean admin = false;
	HttpSession session=null;
	
	@Override
	public void init() throws ServletException { //la méthode init est appellée une seule fois
		System.out.println("init FiltreAjouter");
	}
	
	@Override
	public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		System.out.println("this.acces="+this.acces.toString());
		System.out.println("this.admin="+this.admin.toString());
		
		String sessionId = (String) request.getParameter("sessionId");
		if(sessionId!="" && sessionId!=null) {
			try {//pour gérer le cas où la référence de la session ne mène à aucun objet.
				this.session = HttpSessionCollector.find(sessionId);
				Boolean sessionAcces =(Boolean) this.session.getAttribute("acces");
				Boolean sessionAdmin =(Boolean) this.session.getAttribute("admin");
				if(sessionAcces!=null) {this.acces = sessionAcces;}
				if(sessionAdmin!=null) {this.admin = sessionAdmin;}
			} catch (NullPointerException e) {
				
			}
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
		
		if(this.acces && this.admin) { //on veut ajouter la condition d'être admin pour accéder à la page ajouter
			System.out.println("Le FiltreAjouter laisse passer");
			request.setAttribute("sessionId", sessionId); //on garde l'id de la session
			this.admin=false;
			this.acces=false;//pour éviter que le filtre se souvienne de la connexion trop longtemps.
			chain.doFilter(request, response); //on passe l'information à la servlet Liste
		}
		else {
			System.out.println("Le FiltreAjouter bloque");
			response.sendRedirect("login?echec=2"); //pour rediriger vers la servlet initiale
		}
	}	
}