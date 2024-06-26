package ics.ejb;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import facade.FacadeLocal;
import ics.exceptions.FootBookException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MatchInfoHandler implements IPathHandler {
	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException, FootBookException {
		// TODO Auto-generated method stub
		String matchId = request.getParameter("matchId");
		Match match = facade.findMatch(matchId);

		System.out.println("MatchId: " + matchId);

		Set<User> usersOnMatch = facade.getUsersOnMatch(matchId);
		Set<User> availableUsers = facade.getAvailableUsers();
		
		Set<Match> allMatches = facade.findAllMatches();

		request.setAttribute("availableUsers", availableUsers);
		request.setAttribute("usersOnMatch", usersOnMatch);
		request.setAttribute("match", match);
		
		request.setAttribute("allMatches", allMatches);

		String errorMessage = null;
        String displayStyle = "none"; // Default to not displaying the popup
        request.setAttribute("displayStyle", displayStyle);

		String action = request.getParameter("formType");
		String redirectUrl = request.getContextPath() + "/matchInfo?matchId=" + matchId;
	    

		if ("addUserToMatch".equals(action)) {
			
			if(usersOnMatch.size() < 10) {
			String userId = request.getParameter("userId");
			User user = facade.findUser(userId);
			user.setMatch(match);
			facade.updateUser(user);
			response.sendRedirect(redirectUrl);
			} else {
				errorMessage = "This 5 A-Side Game Is At Its Capacity Of 10 Users!";
				request.setAttribute("errorMessage", errorMessage);
				displayStyle = "block"; // Set to display the popup
				request.setAttribute("displayStyle", displayStyle);
			}
		}
		
		else if("removeUserFromMatch".equals(action)) {
			
			if(usersOnMatch.size() > 1) {
			String userId = request.getParameter("removeUserId");
			User user = facade.findUser(userId);
			user.setMatch(null);
			
			facade.updateUser(user);
			response.sendRedirect(redirectUrl);
		} else {
			errorMessage = "Cannot remove the last user from the match!";
			request.setAttribute("errorMessage", errorMessage);
            displayStyle = "block"; // Set to display the popup
            request.setAttribute("displayStyle", displayStyle);
		}
		}
		
		else if("deleteMatch".equals(action)) {
			String pitchId = match.getPitch().getPitchId();
			facade.deleteMatch(matchId);
			return request.getRequestDispatcher("/pitchInfo?pitchId=" + pitchId);
		}
		else if ("updateMatch".equals(action)) {
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
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
