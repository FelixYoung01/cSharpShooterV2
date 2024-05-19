package ics.ejb;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import facade.FacadeLocal;
import ics.exceptions.FootBookException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PitchInfoHandler implements IPathHandler {

    private static final Logger logger = Logger.getLogger(PitchInfoHandler.class.getName());

    @Override
    public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException, FootBookException {

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
            FacadeLocal facade) throws ServletException, IOException, FootBookException {

        String action = request.getParameter("formType");
        logger.info("Form action received: " + action);

        String pitchId = request.getParameter("pitchId");
        Pitch pitch = facade.findPitch(pitchId);

        Set<User> users = facade.getAvailableUsers();
        Set<Referee> referees = facade.getAllReferees();
        Set<Match> matchesOnPitch = facade.getMatchesOnPitch(pitchId);
        Map<String, Integer> matchUserCounts = new HashMap<>();

        for (Match match : matchesOnPitch) {
            Set<User> usersOnMatch = facade.getUsersOnMatch(match.getMatchId());
            int userCount = usersOnMatch.size();
            matchUserCounts.put(match.getMatchId(), userCount);
        }

        if (matchUserCounts.isEmpty()) {
            System.out.println("No users on pitch");
        }

        request.setAttribute("matchUserCounts", matchUserCounts);
        request.setAttribute("matchesOnPitch", matchesOnPitch);
        request.setAttribute("pitch", pitch);
        request.setAttribute("users", users);
        request.setAttribute("referees", referees);

        // Creating a match handling
        if ("createMatch".equals(action)) {
            String refereeId = request.getParameter("refereeId");
            String userId = request.getParameter("userId");
            String date = request.getParameter("date");
            String time = request.getParameter("time");

            logger.info("Referee ID: " + refereeId);
            logger.info("User ID: " + userId);
            logger.info("Date: " + date);
            logger.info("Time: " + time);

            String matchId = generateMatchId(facade);

            logger.info("Generated matchId: " + matchId);

            // Parsing date and time
            LocalDate parsedDate = LocalDate.parse(date);
            LocalTime parsedTime = LocalTime.parse(time);

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

            // Update user with match info
            user.setMatch(match);
            facade.updateUser(user);

            logger.info("Match created and user updated.");

            response.sendRedirect(request.getContextPath() + "/pitchInfo?pitchId=" + pitchId);
            return null;
        }

        return request.getRequestDispatcher("/pitchInfo.jsp");
    }
    
	private String generateMatchId(FacadeLocal facade) throws FootBookException {
		for (int i = 1; i < 99; i++) {
			String matchId = "M" + String.format("%02d", i);
			if (facade.findMatch(matchId) == null) {
				return matchId;
			}
		}
		return null;
	}
}

