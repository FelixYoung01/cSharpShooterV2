package ics.ejb;

import java.io.IOException;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MatchInfoHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String matchId = request.getParameter("matchId");
		Match match = facade.findMatch(matchId);
		
		Set<User> usersOnMatch = facade.getUsersOnMatch(matchId);
		for(User u : usersOnMatch) {
		 System.out.println("usersOnMatch: " + u.getName());
		}
		request.setAttribute("usersOnMatch", usersOnMatch);
		request.setAttribute("match", match);
		return request.getRequestDispatcher("/matchInfo.jsp");
	}
	
	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException {
        return request.getRequestDispatcher("/matchInfo.jsp");
    }
}


