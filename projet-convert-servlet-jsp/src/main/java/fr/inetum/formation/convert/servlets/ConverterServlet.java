package fr.inetum.formation.convert.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/converter")
public class ConverterServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/pages/converter.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String celsius = req.getParameter("celsius");

		try {
			double celsiusDouble = Double.parseDouble(celsius);
			double fahrenheit = celsiusDouble * 9 / 5 + 32;
			req.setAttribute("resultat", fahrenheit);			
		} catch (Exception e) {
			req.setAttribute("erreur", "Attention à votre saisie");
			// req.getRequestDispatcher("/WEB-INF/pages/converter.jsp").forward(req, resp);
		}
		doGet(req, resp);
	}
}
