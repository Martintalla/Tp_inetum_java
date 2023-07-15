package fr.inetum.servlets;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/maserv")
//@WebServlet(name = "ma-servlet", urlPatterns = "/maserv")
public class MaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doGet");
		System.out.println("Request effectuée par " + request.getRemoteAddr() + " " + request.getRemoteHost());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doPost");
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doPut");
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("doDelete");
	}

	@Override
	public void init() throws ServletException {
		System.out.println("initialisation de la servlet");
	}

	@Override
	public void destroy() {
		System.out.println("Destruction de l'instance de la servlet");
	}
}
