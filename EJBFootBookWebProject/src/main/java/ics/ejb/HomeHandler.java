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
		long matchCount = facade.getMatchCount();
		long userCount = facade.getUserCount();
		long userOnMatchesCount = facade.getUsersOnMatchesCount();
		
		request.setAttribute("pitches", pitches);
		request.setAttribute("matchCount", matchCount);
		request.setAttribute("userCount", userCount);
		request.setAttribute("userOnMatchesCount", userOnMatchesCount);

		
		return request.getRequestDispatcher("/home.jsp");
	}
}
