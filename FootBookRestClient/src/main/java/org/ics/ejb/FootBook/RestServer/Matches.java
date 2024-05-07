package org.ics.ejb.FootBook.RestServer;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import facade.FacadeLocal;
import ics.ejb.Match;

//IN THE JAVA BUILD PATH WE HAVE MADE SURE THAT THE FOOTBOOKEJBPROJECT IS ADDED AS A PROJECT IN THE CLASSPATH.
//THIS IS SO THAT WE CAN ACCESS THE FACADE EJB IN THIS SERVLET.
//AND SO THAT WE CAN ACCESS MATCHES AND OTHER ENTITIES IN THE FOOTBOOKEJBPROJECT.

// Mapping all URLs starting with "/Matches" onto this servlet.
// This way, URLs like /Matches/123 or /Matches/details/456 can be handled by the same servlet

@WebServlet("/Matches/*")
public class Matches extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FacadeLocal facade;
	
       
    public Matches() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null || pathInfo.equals("/")) {
			System.out.println("Fetching all matches");
			System.out.println(pathInfo);
			
			List<Match> matches = facade.findAllMatches();
			sendAsJSon(response, matches);
			
			return;
		}
		String[] splits = pathInfo.split("/");
		
		if (splits.length != 2) {
			System.out.println("Alla2");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}
		
		String id = splits[1];
		Match match = facade.findMatch(id);
		sendAsJson(response, match);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		
		if (pathInfo == null || pathInfo.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}
		
		String[] splits = pathInfo.split("/");

		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}

		String id = splits[1];
		Match match = facade.findMatch(id);
		
		if(match != null) {
            facade.deleteMatch(id);
		}
		sendAsJson(response, match);
	}
	
	
	//Method that gets the certain match and sends it as JSON.
	private void sendAsJson(HttpServletResponse response, Match match) throws IOException { 
		
			PrintWriter out = response.getWriter();
			response.setContentType("application/json");
		
			if (match != null) {
			out.print("{\"Match ID\":");
			out.print("\"" + match.getMatchId() + "\"");
			out.print(",\"Match Date\":");
			out.print("\"" + match.getDate() +"\"");
			out.print(",\"Match Time\":");
			out.print("\"" + match.getTime() +"\"}");
		} else {
			out.print("{ }");
			}
			out.flush();
			}
	
	
	//Method that gets all matches and sends them as JSON.
	private void sendAsJSon(HttpServletResponse response, List<Match> match) throws IOException{
	    // Set up the PrintWriter for writing to the response
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		
		
		//Checks if matches is not null
		if(match != null) {
			//Creates a JsonArrayBuilder
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (Match m : match) {
				// Builds the JSON object for each match and adds it to the array
				JsonObjectBuilder obj = Json.createObjectBuilder();
				obj.add("Match ID", m.getMatchId());
				obj.add("Match Date", m.getDate().toString());
				obj.add("Match Time", m.getTime().toString());
				array.add(obj);
			}
			
			// Create the final JSON Array and write it to the response
			JsonArray jsonArray = array.build();
			out.print(jsonArray.toString());
			
			System.out.println("Match Rest:" + jsonArray);
		}else {
			out.print("[]");
		}
		out.flush();
	}
	
}



