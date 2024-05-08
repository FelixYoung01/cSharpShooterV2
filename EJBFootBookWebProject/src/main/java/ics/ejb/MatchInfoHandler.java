package ics.ejb;

import java.io.IOException;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MatchInfoHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String matchId = request.getParameter("matchId");
		Match match = facade.findMatch(matchId);
		
		request.setAttribute("match", match);
		return request.getRequestDispatcher("/matchInfo.jsp");
	}

}
