package ics.ejb;

import java.io.IOException;
import java.util.List;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class HomeHandler implements IPathHandler {

    @Override
    public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException {
        return request.getRequestDispatcher("/home.jsp");
    }

    @Override
    public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException {

        String type = request.getParameter("type");
        if (type != null && !type.isEmpty()) {
            handleAjaxRequest(request, response, facade, type);
            return null; // Indicate that the request has been handled
        }

        Set<Pitch> pitches = facade.getAllPitches();

        long matchCount = facade.getMatchCount();
        long userCount = facade.getUserCount();
        long userOnMatchesCount = facade.getUsersOnMatchesCount();

        request.setAttribute("pitches", pitches);
        request.setAttribute("matchCount", matchCount);
        request.setAttribute("userCount", userCount);
        request.setAttribute("userOnMatchesCount", userOnMatchesCount);

        HttpSession sessions = request.getSession(false); // Hämtar nuvarande session utan att skapa en ny om den inte redan finns

        // kod-stycke för att räkna antalet besökare
        if (sessions != null && sessions.getAttribute("visited") == null) { // Kolla ifall session finns och ifall den redan har besökt sidan

            int visitorCount = (int) request.getServletContext().getAttribute("sessionCount"); // Hämtar antalet besökare från context
            request.getServletContext().setAttribute("sessionCount", visitorCount + 1); // Ökar antalet besökare med 1
            sessions.setAttribute("visited", true); // Sätter visited till true för att visa att sessionen nu har besökt sidan
        }

        return request.getRequestDispatcher("/home.jsp");
    }

    private void handleAjaxRequest(HttpServletRequest request, HttpServletResponse response, FacadeLocal facade, String type) throws IOException {
        StringBuilder data = new StringBuilder();

        if ("matches".equals(type)) {
            List<Match> matches = facade.findAllMatches();
            data.append("<h2>Matches Registered</h2><ul>");
            for (Match match : matches) {
                data.append("<li>").append(match.getMatchId()).append(": ").append(match.getDate()).append("</li>");
            }
            data.append("</ul>");
        } else if ("users".equals(type)) {
            Set<User> users = facade.getAllUsers();
            data.append("<h2>Users Registered</h2><ul>");
            for (User user : users) {
                data.append("<li>").append(user.getUserId()).append(": ").append(user.getName()).append("</li>");
            }
            data.append("</ul>");
        } else if ("usersInMatches".equals(type)) {
            Set<User> usersInMatches = facade.getUsersInMatches();
            data.append("<h2>Users Registered in Matches</h2><ul>");
            for (User user : usersInMatches) {
                data.append("<li>").append(user.getUserId()).append(": ").append(user.getName()).append("</li>");
            }
            data.append("</ul>");
        } else if ("sessions".equals(type)) {
            int sessionCount = (int) request.getServletContext().getAttribute("sessionCount");
            data.append("<h2>Session Count</h2>");
            data.append("<p>Total Sessions: ").append(sessionCount).append("</p>");
        }

        response.setContentType("text/html");
        response.getWriter().write(data.toString());
    }
}
