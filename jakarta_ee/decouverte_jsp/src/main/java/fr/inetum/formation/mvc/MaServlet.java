package fr.inetum.formation.mvc;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/jspserv")
public class MaServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Methode doGet");
		// ON FAIT ICI TOUS LES TRAITEMENTS QUE L'ON SOUHAITE
		String prenom = request.getParameter("prenom");
		request.setAttribute("pre", prenom);
		
		String msg = "Je suis un message qui provient de la servlet MaServlet";
		request.setAttribute("msg", msg);
		
		String age = request.getParameter("age");
		request.setAttribute("age", age);
		
		LocalDate date =  LocalDate.now();
		request.setAttribute("date", date);

		LocalDateTime heure = LocalDateTime.now();
		request.setAttribute("heure", heure);
		
		String ip = request.getRemoteAddr();
		request.setAttribute("ip", ip);
		
		// A LA FIN, ON PASSE LA MAIN A LA JSP
		request.getRequestDispatcher("/WEB-INF/pages/majsp.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String prenom = req.getParameter("prenom");
		String age = req.getParameter("age");
		System.out.println(String.format("prenom : %s, age : %s ans", prenom, age));
	}
}
