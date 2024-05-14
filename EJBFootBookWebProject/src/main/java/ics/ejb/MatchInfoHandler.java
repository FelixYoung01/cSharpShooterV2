package ics.ejb;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;
import java.util.logging.Logger;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class MatchInfoHandler implements IPathHandler {

	private static final Logger logger = Logger.getLogger(MatchInfoHandler.class.getName());
	
	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String matchId = request.getParameter("matchId");
		Match match = facade.findMatch(matchId);

		System.out.println("MatchId: " + matchId);
		
		String action = request.getParameter("formType");
		logger.info("Form action received: " + action);
		
		Set<User> usersOnMatch = facade.getUsersOnMatch(matchId);
		Set<User> availableUsers = facade.getAvailableUsers();
		
		//Force Initilization of referee to avoid lazy loading exception
		Referee referee = match.getReferee();
		if (referee != null) {
			referee.getRefereeName();
		}
		
		request.setAttribute("availableUsers", availableUsers);
		request.setAttribute("usersOnMatch", usersOnMatch);
		request.setAttribute("match", match);
		
		if ("updateMatch".equals(action)) {
			String date = request.getParameter("matchDate");
			String time = request.getParameter("matchTime");
			
			LocalDate parsedDate = LocalDate.parse(date);
			LocalTime parsedTime = LocalTime.parse(time);
			
			match.setDate(parsedDate);
			match.setTime(parsedTime);
			facade.updateMatch(match);
			logger.info("Match updated with new date and time: " + match);
			
			//Redirect to same page using triggering a get request
			response.sendRedirect(request.getContextPath() + "/matchInfo?matchId=" + matchId);
			return null;
			
			
		} else if ("removeMatch".equals(action)) {
			String pitchId = match.getPitch().getPitchId(); // get pitchId before deleting match
			for (User user : usersOnMatch) {
				user.setMatch(null);
				facade.updateUser(user);
			}
			facade.deleteMatch(match.getMatchId());
			
			return request.getRequestDispatcher("/pitchInfo?pitchId=" + pitchId); // Redirect to the pitchinfo page of pitch
			
		}  else if (request.getParameter("userId") != null) {

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
		String matchId = request.getParameter("matchId");
		Match match = facade.findMatch(matchId);

		System.out.println("MatchId: " + matchId);

		Set<User> usersOnMatch = facade.getUsersOnMatch(matchId);
		Set<User> availableUsers = facade.getAvailableUsers();
		//Force Initilization of referee to avoid lazy loading exception
		Referee referee = match.getReferee();
		if (referee != null) {
			referee.getRefereeName();
		}
		
		request.setAttribute("availableUsers", availableUsers);
		request.setAttribute("usersOnMatch", usersOnMatch);
		request.setAttribute("match", match);
		
		return request.getRequestDispatcher("/matchInfo.jsp");
	}
	
	
}
