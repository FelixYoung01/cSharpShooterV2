package ics.ejb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
	        FacadeLocal facade) throws ServletException, IOException {
	    Set<User> users = facade.getAllUsers();
	    Set<Referee> referees = facade.getAllReferees();
	    List<RefereeLicense> licenses = facade.getAllRefereeLicenses();

	    request.setAttribute("users", users);
	    request.setAttribute("referees", referees);
	    request.setAttribute("licenses", licenses);

	    return request.getRequestDispatcher("/register.jsp");
	}
	

	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {

		Set<User> users = facade.getAllUsers();
		Set<Referee> referees = facade.getAllReferees();
		List<RefereeLicense> Licenses = facade.getAllRefereeLicenses();

		request.setAttribute("users", users);
		request.setAttribute("referees", referees);
		request.setAttribute("licenses", Licenses);

		String action = request.getParameter("formType");


		
			// Kör kodstycket ifall det är en user som ska läggas till //ADD USER CODE

		if ("addUser".equals(action)) {// Kör kodstycket ifall metoden är POST
			String tempAge = request.getParameter("userAge");
			String email = request.getParameter("userEmail");
			String gender = request.getParameter("userGender");
			String name = request.getParameter("userName");
			int age = Integer.parseInt(tempAge);

			User user = new User(age, email, gender, name);
			facade.createUser(user);

			System.out.println("User added");
			response.sendRedirect(request.getRequestURI());
			
			}
		
			// Kör kodstycket ifall det är en dommare som ska läggas till //ADD REFEREE CODE
			else if ("addReferee".equals(action)){
				

			String refName = request.getParameter("refereeName");
			String licenseId = request.getParameter("licenseId");

			RefereeLicense tempLicense = facade.findRefereeLicense(licenseId);

			Referee referee = new Referee(refName, tempLicense);
			facade.createReferee(referee);
			System.out.println("Referee added");

			response.sendRedirect(request.getRequestURI());
			return null;
		}

		// Kör kodstycket ifall metoden är PUT (UPDATE)
		else if ("editUser".equals(action)) {

			String userId = request.getParameter("userId");
			String newName = request.getParameter("userName");
			String newAge = request.getParameter("userAge");
			String newEmail = request.getParameter("userEmail");
			String newGender = request.getParameter("userGender");

			User userToUpdate = facade.findUserById(userId); // Method to find user by ID

			// Handle updating a user:

			if (userToUpdate != null) {
				int age = Integer.parseInt(newAge); // Handle NumberFormatException appropriately.
				userToUpdate.setName(newName);
				userToUpdate.setAge(age);
				userToUpdate.setEmail(newEmail);
				userToUpdate.setGender(newGender);

				facade.updateUser(userToUpdate); // Method to update user in your data source. Implement this in your
													// facade.

				System.out.println("User updated");
				response.sendRedirect(request.getRequestURI());
				return null;
			} else {
				response.getWriter().write("User not found");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST); // Set appropriate status code on failure.
			}
			return null;
		}

	/*	else if (request.getMethod().equalsIgnoreCase("POST")) {
			String action1 = request.getParameter("action");

			if (action1 != null && action1.equals("remove")) {
				String userIdToDelete = request.getParameter("userId");
				if (userIdToDelete != null) {

					facade.deleteUser(userIdToDelete);
					if (userIdToDelete != null) {
						response.getWriter().write("User deleted successfully");
						return null; // We don't need to forward to any JSP page
					} else {
						response.getWriter().write("User not found");
					}
				}
			}
		}
*/

			
			//Removing a user
			else if ("removeUser".equals(action)) {
		        String userId = request.getParameter("userId");

		        User user = facade.findUser(userId);
		        if (user != null) {
		            facade.deleteUser(userId);
		            System.out.println("User removed: " + userId);
		            response.sendRedirect(request.getRequestURI());
		            return null;
		        } else {
		            System.out.println("User not found: " + userId);
		            response.getWriter().write("User not found");
		            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
		            return null;
		        }
			}
		       //Removing a referee	
		        else if ("removeReferee".equals(action)) {
                    String refereeId = request.getParameter("refereeId");

                    Referee referee = facade.findRefereeById(refereeId);
                    if (referee != null) {
                        facade.deleteReferee(refereeId);
                        System.out.println("Referee removed: " + refereeId);
                        response.sendRedirect(request.getRequestURI());
                        return null;
                    } else {
                        System.out.println("Referee not found: " + refereeId);
                        response.getWriter().write("Referee not found");
                        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                        return null;
                    }
                }

			return request.getRequestDispatcher("/register.jsp");
	}
}

