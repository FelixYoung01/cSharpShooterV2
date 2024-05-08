package ics.ejb;

import java.io.IOException;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		Set<Pitch> pitches = facade.getAllPitches();
		
		request.setAttribute("pitches", pitches);
		request.setAttribute("matchCount", facade.getMatchCount());
		
		return request.getRequestDispatcher("/home.jsp");
	}
}
