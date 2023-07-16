package fr.inetum.tp.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import fr.inetum.tp.connection.MaConnexion;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
private Connection connection;
	
	public LoginServlet(Connection connection) {
		this.connection = connection;
	}

	public LoginServlet() throws ClassNotFoundException, SQLException  {
		
			
				connection = MaConnexion.getInstance().getConnection();
	}		
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans doget");
		request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("je suis dans dopost");
		String mdp = request.getParameter("mdp");
		String email = request.getParameter("email");
		request.getSession().setAttribute("email", email);
		request.getSession().setAttribute("mdp", mdp);
		
		
			
			try {
				String requete = "SELECT * FROM Stagiaire WHERE email=? AND mdp= ?";
				PreparedStatement stmt = connection.prepareStatement(requete); 
				
				stmt.setString(1, email);
				stmt.setString(2, mdp);
				
				ResultSet rs = stmt.executeQuery();
				
				if (rs.next()) {
					
					String role = rs.getString("role");
					if (role.equals("ADMIN")) {
						// L'utilsateur est administrateur
						System.out.println("l'utilisateur"+email+"est adminisatrateur");
						response.sendRedirect("liste");
						
					}else if (role.equals("USER")) {
						//l'utilisateur est un utilisateur standard
						System.out.println("l'utilisateur"+email+"est un utilsateur standard");
						response.sendRedirect("liste");
					}
					
				}
				 else {
						request.setAttribute("unknownUser", "email et/ou mot de passe incorrect");
						doGet(request, response);
					
				}
			} catch (SQLException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		


	}
		
		
		
	}


