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

		System.out.println("MatchId: " + matchId);

		Set<User> usersOnMatch = facade.getUsersOnMatch(matchId);
		Set<User> availableUsers = facade.getAvailableUsers();

		request.setAttribute("availableUsers", availableUsers);
		request.setAttribute("usersOnMatch", usersOnMatch);
		request.setAttribute("match", match);
		

		if (request.getParameter("userId") != null) {

			String userId = request.getParameter("userId");
			User user = facade.findUser(userId);
			user.setMatch(match);
			facade.updateUser(user);
			
		}

		return request.getRequestDispatcher("/matchInfo.jsp");
	}

	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		return request.getRequestDispatcher("/matchInfo.jsp");
	}
}
