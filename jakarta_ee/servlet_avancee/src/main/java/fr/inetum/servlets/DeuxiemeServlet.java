package fr.inetum.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/deux")
public class DeuxiemeServlet extends HttpServlet {

	private static final long serialVersionUID = -712954879128166499L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Je suis dans la methode doPost");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Je suis dans la methode doGet");
		
		resp.setContentType("text/html;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		
		try {
			// ICI ON CONSTRUIT LA REPONSE
			out.println("<!DOCTYPE html>");
			out.println("<html>"
					+ "<head>"
					+ "<meta charset=UTF-8/>"
					+ "<title>Deuxieme Servlet</title>"
					+ "</head>"
					+ "<body>"
					+ "<h1 style='color: red'>Un petit bonjour dépuis la servlet avancée</h1>"
					+ "</body>"
					+ "</html>");
		} catch (Exception e) {
			out.close();
		}
		
	}
}
