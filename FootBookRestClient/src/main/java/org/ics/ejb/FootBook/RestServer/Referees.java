package org.ics.ejb.FootBook.RestServer;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import facade.FacadeLocal;

@WebServlet("/Referees/*")
public class Referees extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private FacadeLocal facade;

	public Referees() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		// Handle the `/Referees/ids` endpoint to return all Referee IDs
		if (pathInfo != null && pathInfo.equals("/ids")) {
			List<String> refereeIds = facade.findAllRefereeIds();
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (String id : refereeIds) {
				arrayBuilder.add(id);
			}
			response.setContentType("application/json");
			response.getWriter().print(arrayBuilder.build().toString());
			return;
		}

		// Handle other referee-related paths if necessary
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
	}
}
