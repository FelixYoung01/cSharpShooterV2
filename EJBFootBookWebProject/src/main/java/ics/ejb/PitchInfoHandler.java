package ics.ejb;

import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import java.util.HashMap;
import java.util.Map;

import java.util.Set;
import java.util.logging.Logger;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PitchInfoHandler implements IPathHandler {

	private static final Logger logger = Logger.getLogger(PitchInfoHandler.class.getName());
	
	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException {
		
		String pitchId = request.getParameter("pitchId");
		Pitch pitch = facade.findPitch(pitchId);
		
		Set<User> users = facade.getAvailableUsers();
		Set<Referee> referees = facade.getAllReferees();
		Set<Match> matchesOnPitch = facade.getMatchesOnPitch(pitchId);
		
		request.setAttribute("matchesOnPitch", matchesOnPitch);
		request.setAttribute("pitch", pitch);
		request.setAttribute("users", users);
		request.setAttribute("referees", referees);
		
		return request.getRequestDispatcher("/pitchInfo.jsp");
    }
	

@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {

		
		String action = request.getParameter("formType");
		logger.info("Form action received: " + action);
		
		String pitchId = request.getParameter("pitchId");
		Pitch pitch = facade.findPitch(pitchId);
		
		Set<User> users = facade.getAvailableUsers();
		Set<Referee> referees = facade.getAllReferees();

		Set<Match> matchesOnPitch = facade.getMatchesOnPitch(pitchId);
		Map<String, Integer> matchUserCount = new HashMap<>();

		for (Match match : matchesOnPitch) {
			
			Set<User> usersOnMatch = facade.getUsersOnMatch(match.getMatchId());
			
				int userCount = usersOnMatch.size();
				matchUserCount.put(match.getMatchId(), userCount);
			}
		
			if (matchUserCount.isEmpty()) {
				System.out.println("No users on pitch");
			}

		request.setAttribute("matchUserCount", matchUserCount);
		request.setAttribute("matchesOnPitch", matchesOnPitch);
		request.setAttribute("pitch", pitch);

		request.setAttribute("users", users);
		request.setAttribute("referees", referees);
		
		
		///Creating a match handling
		if("createMatch".equals(action)) {
			
			String refereeId = request.getParameter("refereeId");
			String userId = request.getParameter("userId");
			String date = request.getParameter("date");
			String time = request.getParameter("time");
			
			logger.info("Referee ID: " + refereeId);
			logger.info("User ID: " + userId);
			logger.info("Date: " + date);
			logger.info("Time: " + time);
			
			// parsing date and time
			LocalDate parsedDate = LocalDate.parse(date);
			LocalTime parsedTime = LocalTime.parse(time);
			
			//Check if a match is unique
			//boolean matchExists = facade.isMatchUnique(pitchId, parsedDate, parsedTime);
			
			/*if(matchExists) {
				logger.info("A match already exists on this date and time. Please select another date and time.");
				request.setAttribute("errorMessage", "A match already exists on this date and time. Please select another date and time.");
				return request.getRequestDispatcher("/pitchInfo.jsp");
			}*/
			
			
			//Check if referee is booked on other match on same date and time
			/*boolean isRefereeBooked = facade.isRefereeBooked(refereeId, parsedDate, parsedTime);
			logger.info("Is referee booked: " + isRefereeBooked);
			if (!isRefereeBooked) {
				logger.info("Referee is already booked for this date and time. Please select another date and time.");
				request.setAttribute("errorMessage", "Referee is already booked for this date and time. Please select another date and time.");
				return request.getRequestDispatcher("/pitchInfo.jsp");
			}*/
			
			String matchId = generateMatchId();
			
			do {
				//generate matchId until its unique
               matchId = generateMatchId();
			}
			while(facade.findMatch(matchId) != null);
			
			logger.info("Generated matchId: " + matchId);


			
			Referee refereeForMatch = facade.findRefereeById(refereeId);
			User user = facade.findUserById(userId);
			
			
			System.out.println("Creating match...");
		    System.out.println("Pitch ID: " + pitchId);
		    System.out.println("Referee ID: " + refereeId);
		    System.out.println("User ID: " + userId);
		    System.out.println("Match Date: " + date);
		    System.out.println("Match Time: " + time);
		    
			Match match = new Match(matchId, refereeForMatch, pitch, parsedDate, parsedTime);
			facade.createMatch(match);
			
			//Update userid matchid entry
			user.setMatch(match);
			facade.updateUser(user);
			
			logger.info("Match created and user update.");
			
			response.sendRedirect(request.getContextPath() + "/matchInfo?matchId=" + matchId);
            return null;
		}
		
		return request.getRequestDispatcher("/pitchInfo.jsp");
	}



	private String generateMatchId() {
		Random random = new Random();
		int number = random.nextInt(100);
		return String.format("M%02d" , number);
	}
}


