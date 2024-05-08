package ics.ejb;

import java.io.IOException;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HomeHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		Set<Pitch> pitches = facade.getAllPitches();
		
		request.setAttribute("pitches", pitches);
		request.setAttribute("matchCount", facade.getMatchCount());
		
		HttpSession sessions = request.getSession(false); //Hämtar nuvarande session utan att skapa en ny om den inte redan finns


		//Code for session tracking
		if (sessions != null && sessions.getAttribute("visited") == null) { //Kolla ifall session finns och ifall den redan har besökt sidan
			
			ServletContext applications = request.getServletContext(); //Hämtar applikationens attributer
			int visitorCount = (int) applications.getAttribute("sessionCount"); //Hämtar antalet besökare från context
			applications.setAttribute("sessionCount", visitorCount + 1); //Ökar antalet besökare med 1
			sessions.setAttribute("visited", true); //Sätter visited till true för att visa att sessionen nu har besökt sidan
		}
		
		return request.getRequestDispatcher("/home.jsp");
	}
}
