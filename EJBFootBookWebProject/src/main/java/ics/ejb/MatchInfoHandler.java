package ics.ejb;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
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
		
		String action = request.getParameter("formType");
		String redirectUrl = request.getContextPath() + "/matchInfo?matchId=" + matchId;
	    

		if ("addUserToMatch".equals(action)) {

			String userId = request.getParameter("userId");
			User user = facade.findUser(userId);
			user.setMatch(match);
			facade.updateUser(user);
			response.sendRedirect(redirectUrl);
		}
		
		else if("removeUserFromMatch".equals(action)) {
			String userId = request.getParameter("removeUserId");
			User user = facade.findUser(userId);
			user.setMatch(null);
			facade.updateUser(user);
			response.sendRedirect(redirectUrl);
		}
		
		else if("deleteMatch".equals(action)) {
			String pitchId = match.getPitch().getPitchId();
			facade.deleteMatch(matchId);
			return request.getRequestDispatcher("/pitchInfo?pitchId=" + pitchId);
		}
		else if ("updateMatch".equals(action)) {
			String date = request.getParameter("matchDate");
			String time = request.getParameter("matchTime");
			
			LocalDate parsedDate = LocalDate.parse(date);
			LocalTime parsedTime = LocalTime.parse(time);
			
			match.setDate(parsedDate);
			match.setTime(parsedTime);
			facade.updateMatch(match);
			
			response.sendRedirect(redirectUrl);
			}

		return request.getRequestDispatcher("/matchInfo.jsp");
	}

	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		return request.getRequestDispatcher("/matchInfo.jsp");
	}
}
