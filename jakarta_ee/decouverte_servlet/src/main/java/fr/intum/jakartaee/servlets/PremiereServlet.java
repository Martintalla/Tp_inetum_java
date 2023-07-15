package fr.intum.jakartaee.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PremiereServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TRAITEMENT DES REQUETES GET
		System.out.println("Je suis dans la methode doGet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TRAITEMENT DES REQUETES 
		System.out.println("Je suis dans la methode doPost");
	}
}