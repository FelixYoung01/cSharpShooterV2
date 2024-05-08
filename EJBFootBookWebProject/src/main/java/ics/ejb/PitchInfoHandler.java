package ics.ejb;

import java.io.IOException;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PitchInfoHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		
		String pitchId = request.getParameter("pitchId");
		Pitch pitch = facade.findPitch(pitchId);
		
		Set<Match> matchesOnPitch = facade.getMatchesOnPitch(pitchId);
		
		request.setAttribute("matchesOnPitch", matchesOnPitch);
		request.setAttribute("pitch", pitch);
		
		return request.getRequestDispatcher("/pitchInfo.jsp");
	}
	
}
