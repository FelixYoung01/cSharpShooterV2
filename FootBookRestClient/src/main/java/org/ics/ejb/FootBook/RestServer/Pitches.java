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

@WebServlet("/Pitches/*")
public class Pitches extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@EJB
	private FacadeLocal facade;

	public Pitches() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		// Check for `/Pitches/ids` endpoint
		if (pathInfo != null && pathInfo.equals("/ids")) {
			List<String> pitchIds = facade.findAllPitchIds(); // Ensure this method returns a list of pitch IDs
			System.out.println("Pitch IDs: " + pitchIds);
			JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
			for (String id : pitchIds) {
				arrayBuilder.add(id);
			}
			response.setContentType("application/json");
			response.getWriter().print(arrayBuilder.build().toString());
			return;
		}

		// Handle other pitch-related paths as needed
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
	}
	
	
}
