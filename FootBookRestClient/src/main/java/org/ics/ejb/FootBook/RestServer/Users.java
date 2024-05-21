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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import facade.FacadeLocal;
import ics.ejb.User;
import ics.exceptions.FootBookException;

@WebServlet("/Users/*")//Path to access this servlet. This servlet will handle all requests to /Users
public class Users extends HttpServlet { // HttpServlet is a class that provides methods, such as doGet, doPost, doPut, doDelete, for handling HTTP requests
	private static final long serialVersionUID = 1L; // A unique serial version identifier. 

	@EJB
	FacadeLocal facade; // Import the EJB FacadeLocal interface in order to use the methods in the facade and interact with the database

	public Users() { //default constructor
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) //Method to handle GET requests / retrieve data
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo(); // Get the path info from the request. Path info is the part of the URL that follows the servlet path (/Users/...) but comes before any query string
		if (pathInfo == null || pathInfo.equals("/")) { // If the path info is null or empty, return all users. This because the path /Users/ is used to get all users
			System.out.println("Alla");
			System.out.println(pathInfo);

			// Get the set of users from the facade and convert it to a list
			Set<User> userSet = null; // Set is a collection that contains no duplicate elements
			try {
				userSet = facade.getAllUsers(); // Get all users from the database and store them in the userSet
			} catch (FootBookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<User> allUsers = new ArrayList<>(userSet); // Convert the userSet to a list of users in order to send it as JSON. this because JSON does not support sets

			sendAsJson(response, allUsers); // Send the list of users as JSON. this because the response is in JSON format

			return;
		}
		String[] splits = pathInfo.split("/"); // Split the path info into an array of strings. Meaning that if the path is /Users/1, the array will contain two elements: "Users" and "1"
		if (splits.length != 2) { // If the length of the array is not 2, return a bad request error. because the path should be /Users/id
			System.out.println("Alla2");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String id = splits[1]; // Get the id from the path info, stored in the second element of the array
		User user = null; // Create a user object to store the user with the id
		try {
			user = facade.findUserById((id)); // Get the user with the id from the database and store it in the user object
		} catch (FootBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendAsJson(response, user); // Send the user as JSON. this because the response is in JSON format
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo(); // Get the path info from the request. Path info is the part of the URL that follows the servlet path (/Users/...) but comes before any query string
		if (pathInfo == null || pathInfo.equals("/")) { // If the path info is null or empty, create a new user. This because the path /Users/ is used to create a new user
			BufferedReader reader = request.getReader(); // Get the reader from the request. The reader is used to read the input stream from the client and parse the JSON

			User u = parseJsonUser(reader); // Parse the JSON input and store it in a user object. WE parse the JSON because the input is in JSON format

			try {
				u = facade.createUser(u); // Create a new user in the database and store the user object in u
				sendAsJson(response, u); // send successful response as JSON
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
				response.getWriter().write("{\"error\": \"User ID already exists.\"}");
				response.getWriter().flush();
				System.out.println("duplicate key");
				return;
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) //Method to handle PUT requests / update data
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo(); // Get the path info from the request. Path info is the part of the URL that follows the servlet path (/Users/...) but comes before any query string

		if (pathInfo == null || pathInfo.equals("/")) { // If the path info is null or empty, return a bad request error. This because the path should be /Users/id
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String[] splits = pathInfo.split("/"); // Split the path info into an array of strings. Meaning that if the path is /Users/1, the array will contain two elements: "Users" and "1"
		if (splits.length != 2) { // If the length of the array is not 2, return a bad request error. because the path should be /Users/id
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String id = splits[1]; // Get the id from the path info, stored in the second element of the array, for example, if the path is /Users/1, the id is 1

		BufferedReader reader = request.getReader(); // Get the reader from the request. The reader is used to read the input stream from the client and parse the JSON

		User existingUser = null; // Create a user object to store the existing user
		try {
			existingUser = facade.findUserById(id); // Get the user with the id from the database and store it in the existingUser
		} catch (FootBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User updatedUser = parseJsonUser(reader); // parse the json input and store it in the updatedUser because the input is in JSON format

		// check if user exists using the existingUser
		if (existingUser == null) {// If the user does not exist, return a not found error
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
			response.getWriter().write("User not found.");
			return;
		}

		// Update only the fields that are provided in the JSON input
		if (updatedUser.getName() != null) { // If the name is not null, update the name
			existingUser.setName(updatedUser.getName());
		}
		if (updatedUser.getAge() != 0) { // If the age is not 0, update the age
			existingUser.setAge(updatedUser.getAge());
		}
		if (updatedUser.getEmail() != null) { // If the email is not null, update the email
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getGender() != null) { // If the gender is  not null, update the gender
			existingUser.setGender(updatedUser.getGender());
		}

		try {
			facade.updateUser(existingUser); // Update the user in the database by calling the updateUser method in the facade
			sendAsJson(response, existingUser); // Send the updated user as JSON to the client as a response to the PUT request
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("An error occurred while updating the user.");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response) //Method to handle DELETE requests / delete data
			throws ServletException, IOException { 
		String pathInfo = request.getPathInfo(); // Get the path info from the request. Path info is the part of the URL that follows the servlet path (/Users/...) but comes before any query string
		if (pathInfo == null || pathInfo.equals("/")) { // If the path info is null or empty, return a bad request error. This because the path should be /Users/id
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String[] splits = pathInfo.split("/"); // Split the path info into an array of strings. Meaning that if the path is /Users/1, the array will contain two elements: "Users" and "1"
		if (splits.length != 2) { // If the length of the array is not 2, return a bad request error. because the path should be /Users/id 
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String id = splits[1]; // Get the id from the path info, stored in the second element of the array, for example, if the path is /Users/1, the id is 1
		User user = null;
		try {
			user = facade.findUserById(id); // Get the user with the id from the database and store it in the user object
		} catch (FootBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (user != null) {
			try {
				facade.deleteUser(id);
			} catch (FootBookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		sendAsJson(response, user);
	}
//doGet: When fetching a single user by ID. doPost: When creating a new user and returning the created user. doPut: When updating an existing user and returning the updated user. doDelete: When deleting a user and returning the deleted user.
	private void sendAsJson(HttpServletResponse response, User user) throws IOException { //the sendAsJson method is used to send the user as JSON to the client. This is needed because the response in the doGet, doDelete, doPut, doPost method is in JSON format
		PrintWriter out = response.getWriter(); // Get the writer from the response. The writer is used to write the response to the client, in order to send the user as JSON
		response.setContentType("application/json"); // Set the content type of the response to JSON so that the client knows that the response is in JSON format
		response.setCharacterEncoding("UTF-8"); // Set the character encoding of the response to UTF-8 so that the client can read the response correctly

		if (user != null) { // If the user is not null, send the user as JSON to the client
			out.print("{");
			out.print("\"User ID\":");
			out.print("\"" + user.getUserId() + "\","); // Print the user id as a string
			out.print("\"Name\":");
			out.print("\"" + user.getName() + "\","); // Print the name as a string
			out.print("\"Age\":");
			out.print("\"" + user.getAge() + "\","); // Print the age as a string
			out.print("\"Email\":");
			out.print("\"" + user.getEmail() + "\","); // Print the email as a string
			out.print("\"Gender\":");
			out.print("\"" + user.getGender() + "\""); // Print the gender as string
			out.print("}");
		} else {
			out.print("{}"); // If the user is null, send an empty JSON object to the client
		}

		out.flush(); // Flush makes sure that the response is sent to the client
	}

	//doGet: When fetching all users.
	private void sendAsJson(HttpServletResponse response, List<User> users) throws IOException { // we use the List<User> users to send all users as JSON to the client. Because in order to send multiple users as JSON, we need to use a list
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter(); // Get the writer from the response. The writer is used to write the response to the client, in order to send the users as JSON
		if (users != null) { // If the list of users is not null, send the users as JSON to the client
			JsonArrayBuilder array = Json.createArrayBuilder(); // Create a JSON array builder to build the JSON array. since JSON does not support lists, we need to convert the list to a JSON array
			for (User u : users) {
				JsonObjectBuilder o = Json.createObjectBuilder(); // Create a JSON object builder to build the JSON object. since JSON does not support objects, we need to convert the user to a JSON object
				o.add("Id", String.valueOf(u.getUserId())); // Add the user id to the JSON object
				o.add("Name", u.getName());// Add the name to the JSON object
				o.add("Age", String.valueOf(u.getAge()));// Add the age to the JSON object
				o.add("Email", u.getEmail());// Add the email to the JSON object
				o.add("Gender", u.getGender());

				array.add(o);// Add the JSON object to the JSON array
			}
			JsonArray jsonArray = array.build(); // Build the JSON array
			System.out.println("User Rest: " + jsonArray); // Print the JSON array to the console
			out.print(jsonArray);
		} else {
			out.print("[]");
		}
		out.flush();
	}

	//doPost: When creating a new user by parsing the JSON input. //doPut: When updating an existing user by parsing the JSON input.
	private User parseJsonUser(BufferedReader br) { 
		// javax.json-1.0.4.jar
		JsonReader jsonReader = null; // Create a JSON reader to read the JSON input 
		JsonObject jsonRoot = null; // Create a JSON object to store the JSON input

		jsonReader = Json.createReader(br); // Create a JSON reader from the buffered reader. This is used to read the JSON input
		jsonRoot = jsonReader.readObject(); // Read the JSON input and store it in the JSON object

		System.out.println("JsonRoot: " + jsonRoot); // Print the JSON object to the console for debugging

		User user = new User(); // Create a new user object to store the user from the JSON input
		user.setUserId(jsonRoot.getString("User ID", null)); // Set the user id from the JSON input to the user object
		user.setName(jsonRoot.getString("Name", null)); // Set the name from the JSON input to the user object

		// Handle Age field separately to avoid NumberFormatException
		String ageStr = jsonRoot.getString("Age", null); // Get the age from the JSON input as a string
		if (ageStr != null) {
			try {
				user.setAge(Integer.parseInt(ageStr));
			} catch (NumberFormatException e) {
				user.setAge(0); // Set to a default value or handle as needed
			}
		}

		user.setEmail(jsonRoot.getString("Email", null));
		user.setGender(jsonRoot.getString("Gender", null));
		return user; // Return the user object
	}

}
