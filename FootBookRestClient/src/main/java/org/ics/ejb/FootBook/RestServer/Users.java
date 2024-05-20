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

@WebServlet("/Users/*")
public class Users extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	FacadeLocal facade;

	public Users() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			System.out.println("Alla");
			System.out.println(pathInfo);

			// Get the set of users from the facade and convert it to a list
			Set<User> userSet = null;
			try {
				userSet = facade.getAllUsers();
			} catch (FootBookException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			List<User> allUsers = new ArrayList<>(userSet);

			sendAsJson(response, allUsers);

			return;
		}
		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			System.out.println("Alla2");
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String id = splits[1];
		User user = null;
		try {
			user = facade.findUserById((id));
		} catch (FootBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sendAsJson(response, user);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			BufferedReader reader = request.getReader();

			User u = parseJsonUser(reader);

			try {
				u = facade.createUser(u);
				sendAsJson(response, u); // send successful response
			} catch (Exception e) {
				response.setStatus(HttpServletResponse.SC_CONFLICT); // 409 Conflict
				response.getWriter().write("{\"error\": \"User ID already exists.\"}");
				response.getWriter().flush();
				System.out.println("duplicate key");
			}
		}
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();

		if (pathInfo == null || pathInfo.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}

		String id = splits[1];

		BufferedReader reader = request.getReader();

		User existingUser = null;
		try {
			existingUser = facade.findUserById(id);
		} catch (FootBookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		User updatedUser = parseJsonUser(reader); // parse the json

		// check if user exists using the existingUser
		if (existingUser == null) {
			response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404 Not Found
			response.getWriter().write("User not found.");
			return;
		}

		// Update only the fields that are provided in the JSON input
		if (updatedUser.getName() != null) {
			existingUser.setName(updatedUser.getName());
		}
		if (updatedUser.getAge() != 0) {
			existingUser.setAge(updatedUser.getAge());
		}
		if (updatedUser.getEmail() != null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		if (updatedUser.getGender() != null) {
			existingUser.setGender(updatedUser.getGender());
		}

		try {
			facade.updateUser(existingUser);
			sendAsJson(response, existingUser);
		} catch (Exception e) {
			e.printStackTrace();
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().write("An error occurred while updating the user.");
		}
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String[] splits = pathInfo.split("/");
		if (splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String id = splits[1];
		User user = null;
		try {
			user = facade.findUserById(id);
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

	private void sendAsJson(HttpServletResponse response, User user) throws IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		if (user != null) {
			out.print("{");
			out.print("\"User ID\":");
			out.print("\"" + user.getUserId() + "\",");
			out.print("\"Name\":");
			out.print("\"" + user.getName() + "\",");
			out.print("\"Age\":");
			out.print("\"" + user.getAge() + "\",");
			out.print("\"Email\":");
			out.print("\"" + user.getEmail() + "\",");
			out.print("\"Gender\":");
			out.print("\"" + user.getGender() + "\"");
			out.print("}");
		} else {
			out.print("{}");
		}

		out.flush();
	}

	private void sendAsJson(HttpServletResponse response, List<User> users) throws IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		if (users != null) {
			JsonArrayBuilder array = Json.createArrayBuilder();
			for (User u : users) {
				JsonObjectBuilder o = Json.createObjectBuilder();
				o.add("Id", String.valueOf(u.getUserId()));
				o.add("Name", u.getName());
				o.add("Age", String.valueOf(u.getAge()));
				o.add("Email", u.getEmail());
				o.add("Gender", u.getGender());

				array.add(o);
			}
			JsonArray jsonArray = array.build();
			System.out.println("User Rest: " + jsonArray);
			out.print(jsonArray);
		} else {
			out.print("[]");
		}
		out.flush();
	}

	// Method to add a user
	private User parseJsonUser(BufferedReader br) {
		// javax.json-1.0.4.jar
		JsonReader jsonReader = null;
		JsonObject jsonRoot = null;

		jsonReader = Json.createReader(br);
		jsonRoot = jsonReader.readObject();

		System.out.println("JsonRoot: " + jsonRoot);

		User user = new User();
		user.setUserId(jsonRoot.getString("User ID", null));
		user.setName(jsonRoot.getString("Name", null));

		// Handle Age field separately to avoid NumberFormatException
		String ageStr = jsonRoot.getString("Age", null);
		if (ageStr != null) {
			try {
				user.setAge(Integer.parseInt(ageStr));
			} catch (NumberFormatException e) {
				user.setAge(0); // Set to a default value or handle as needed
			}
		}

		user.setEmail(jsonRoot.getString("Email", null));
		user.setGender(jsonRoot.getString("Gender", null));
		return user;
	}

}
