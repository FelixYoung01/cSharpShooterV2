package org.ics.ejb.FootBook.RestServer;

import jakarta.ejb.EJB;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.json.JsonReader;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import facade.FacadeLocal;
import ics.eao.PitchEAOLocal;
import ics.eao.RefereeEAOLocal;
import ics.ejb.Match;
import ics.ejb.Pitch;
import ics.ejb.Referee;

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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();

		// Check if the request is for the "/ids" path to fetch a list of all match IDs
		if (pathInfo != null && pathInfo.equals("/ids")) {
	        // Fetch the list of IDs only
	        List<String> matchIds = facade.findAllMatchIds();
	        System.out.println("Match IDs: " + matchIds);
	        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
	        for (String id : matchIds) {
	            arrayBuilder.add(id);
	        }
	        response.setContentType("application/json");
	        response.getWriter().print(arrayBuilder.build().toString());
	        return;
	    }

		// Otherwise, handle fetching a specific match
		if (pathInfo == null || pathInfo.equals("/")) {
			System.out.println("Fetching all matches");

			List<Match> matches = facade.findAllMatches();
			sendAsJSon(response, matches);
			return;
		}

		String[] splits = pathInfo.split("/");

		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}

		String id = splits[1];
		Match match = facade.findMatch(id);
		if (match != null) {
			sendAsJson(response, match);
		} else {
			response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found for ID: " + id);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			BufferedReader reader = request.getReader(); // Get the reader from the request
			Match match = parseJsonMatch(reader); // Parse the JSON to a Match object

			try {
				match = facade.createMatch(match); // Create the match in the database
			} catch (Exception e) {
				System.out.println("Duplicate Match ID Key");// If the match already exists, return null
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid JSON");// Send an error response
				return;// Return from the method
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		// Check if the path is valid, if not, return a bad request error
		if (pathInfo == null || pathInfo.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}

		// Split the path to get the match ID, which is the second part of the path,
		// after the slash
		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid URL");
			return;
		}

		String id = splits[1];
		// Update the match in the database, and set the updated match to the match
		// object, which will be returned

		// Retrieve the existing match using the match ID
		Match existingMatch;
		try {
			existingMatch = facade.findMatch(id);
			if (existingMatch == null) {
				response.sendError(HttpServletResponse.SC_NOT_FOUND, "Match not found");
				return;
			}
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving match");
			return;
		}

		// Get the reader from the request containing the updated JSON data
		BufferedReader reader = request.getReader();
		Match updatedData = parseJsonMatch(reader);

		// Update the existing match fields with the new data, without changing the
		// match ID
		// This will depend on your specific fields and the structure of the `Match`
		// class
		existingMatch.setDate(updatedData.getDate());
		existingMatch.setTime(updatedData.getTime());

		try {
			Match updatedMatch = facade.updateMatch(existingMatch);
			sendAsJson(response, updatedMatch); // Send the updated match as JSON
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error updating match");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

		if (match != null) {
			facade.deleteMatch(id);
		}
		sendAsJson(response, match);
	}

	// Connected to the GET method, this method sends a match as JSON if it exists
	private void sendAsJson(HttpServletResponse response, Match match) throws IOException {
		PrintWriter out = response.getWriter(); // Set up the PrintWriter for writing to the response
		response.setContentType("application/json"); // Set the response type to JSON

		if (match != null) { // Check if the match is not null
			JsonObjectBuilder objBuilder = Json.createObjectBuilder(); // Create a JSON object builder

			// Add basic match information
			objBuilder.add("Match ID", match.getMatchId());
			objBuilder.add("Match Date", match.getDate().toString());
			objBuilder.add("Match Time", match.getTime().toString());

			// Add the additional Pitch ID and Referee ID fields
			objBuilder.add("Pitch ID", match.getPitch().getPitchId());
			objBuilder.add("Referee ID", match.getReferee().getRefereeId());

			// Build the final JSON object
			JsonObject jsonObject = objBuilder.build();
			out.print(jsonObject.toString());
		} else {
			out.print("{}"); // Return an empty JSON object if the match is null
		}

		out.flush(); // Flush the output to send it to the client
	}

	// CONNECTED TO THE GET METHOD, THIS METHOD SENDS ALL MATCHES AS JSON,, IF THEY
	// EXIST.
	private void sendAsJSon(HttpServletResponse response, List<Match> match) throws IOException {
		// Set up the PrintWriter for writing to the response
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");

		// Checks if matches is not null
		if (match != null) {
			// Creates a JsonArrayBuilder
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
		} else {
			out.print("[]");
		}
		out.flush();
	}

	// CONNECTED TO THE POST METHOD, THIS METHOD PARSES THE JSON OBJECT TO A MATCH
	// OBJECT.

	@EJB
	private PitchEAOLocal pitchEAO; // Inject the Pitch EAO
	@EJB
	private RefereeEAOLocal refereeEAO; // Inject the Referee EAO

	private Match parseJsonMatch(BufferedReader bufferReader) {
		JsonReader jsonReader = Json.createReader(bufferReader);
		JsonObject jsonRoot = jsonReader.readObject();

		System.out.println("JSON Root: " + jsonRoot);

		// Create a new Match object to be populated and returned
		Match match = new Match();
		match.setMatchId(jsonRoot.getString("Match ID"));

		// Parse the date
		String dateString = jsonRoot.getString("Match Date");
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		try {
			LocalDate matchDate = LocalDate.parse(dateString, dateFormatter);
			match.setDate(matchDate);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid date format: " + e.getMessage());
		}

		// Parse the time
		String timeString = jsonRoot.getString("Match Time");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

		try {
			LocalTime matchTime = LocalTime.parse(timeString, timeFormatter);
			match.setTime(matchTime);
		} catch (DateTimeParseException e) {
			System.out.println("Invalid time format: " + e.getMessage());
		}

		// Retrieve and set the Pitch object using the Pitch ID
		String pitchId = jsonRoot.getString("Pitch ID");
		Pitch pitch = pitchEAO.findPitchById(pitchId);
		if (pitch != null) {
			match.setPitch(pitch);
		} else {
			System.out.println("Pitch not found for ID: " + pitchId);
		}

		// Retrieve and set the Referee object using the Referee ID
		String refereeId = jsonRoot.getString("Referee ID");
		Referee referee = refereeEAO.findRefereeById(refereeId);
		if (referee != null) {
			match.setReferee(referee);
		} else {
			System.out.println("Referee not found for ID: " + refereeId);
		}

		return match;
	}
}
