package ics.ejb;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PitchInfoHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {

		String pitchId = request.getParameter("pitchId");
		Pitch pitch = facade.findPitch(pitchId);

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

		return request.getRequestDispatcher("/pitchInfo.jsp");
	}

	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		return request.getRequestDispatcher("/pitchInfo.jsp");
	}

}
