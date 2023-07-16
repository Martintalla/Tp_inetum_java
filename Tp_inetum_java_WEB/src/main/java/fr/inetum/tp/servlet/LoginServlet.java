package fr.inetum.tp.servlet;

import java.io.IOException;
import java.sql.SQLException;

import fr.inetum.tp.entities.Users;
import fr.inetum.tp.services.IStagiaireService;
import fr.inetum.tp.services.StagiaireService;
import fr.inetum.tp.utils.AppUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IStagiaireService serviceStag;
	public void init() {
		try {
			serviceStag = new StagiaireService();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
		
			
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String mdp = request.getParameter("mdp");
		String email = request.getParameter("email");
		System.out.println(AppUtil.hashPassword(mdp));
		Users user = new Users(email, mdp);
		request.getSession().setAttribute("user", user);
		
		
		if (serviceStag.verifyCredentials(email, mdp)==true) {
			
			if (serviceStag.verifyRole(email, mdp).equalsIgnoreCase("ADMIN")) {
				System.out.println(serviceStag.verifyRole(email, mdp));
				
				response.sendRedirect("liste");
				
			}else if (serviceStag.verifyRole(email, mdp).equalsIgnoreCase("USER")){
				System.out.println(serviceStag.verifyRole(email, mdp));
				response.sendRedirect("liste_user");
			}
			
		}else {
			request.setAttribute("uknownUser", "login et/ou mot de passe incorrect");
			System.out.println("les identifiants sont incorrects");
			doGet(request, response);
		}
		
		
		
	}
}

