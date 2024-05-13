package ics.ejb;

import java.io.IOException;
import java.util.Set;
import java.util.List;

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
		List<RefereeLicense> licenses = facade.getAllRefereeLicenses();

		request.setAttribute("users", users);
		request.setAttribute("referees", referees);
		request.setAttribute("licenses", licenses);

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
      return null;
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

			// Log received parameters to verify that they are correct
			System.out.println("Received Update for UserID: " + userId);
			System.out.println("New Name: " + newName);
			System.out.println("New Age: " + newAge);
			System.out.println("New Email: " + newEmail);
			System.out.println("New Gender: " + newGender);

			User userToUpdate = facade.findUserById(userId);

			if (userToUpdate != null) {
				System.out.println("User found, updating...");
				int age = Integer.parseInt(newAge); // No try-catch block
				userToUpdate.setName(newName);
				userToUpdate.setAge(age);
				userToUpdate.setEmail(newEmail);
				userToUpdate.setGender(newGender);

				User updateSuccessful = facade.updateUser(userToUpdate);


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

				System.out.println("Update result: " + updateSuccessful); // Debugging

				if (updateSuccessful != null) {
					System.out.println("User successfully updated.");
					response.sendRedirect(request.getRequestURI());

				} else {
					System.out.println("User update failed.");
					response.getWriter().write("Update failed");


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


		else if ("editReferee".equals(action)) {
			String refereeId = request.getParameter("refereeId");
			String newRefereeName = request.getParameter("refereeName");
			String newLicenseId = request.getParameter("licenseId");

			// Log received parameters to verify correctness
			System.out.println("Received Update for RefereeID: " + refereeId);
			System.out.println("New Referee Name: " + newRefereeName);
			System.out.println("New License ID: " + newLicenseId);

			Referee refereeToUpdate = facade.findRefereeById(refereeId);
			RefereeLicense license = facade.findRefereeLicense(newLicenseId); // Fetch the license

			if (refereeToUpdate != null && license != null) {
				System.out.println("Referee and License found, updating...");
				refereeToUpdate.setRefereeName(newRefereeName);
				refereeToUpdate.setRefereeLicense(license); // Set the fetched license

				Referee updateSuccessful = facade.updateReferee(refereeToUpdate);
				if (updateSuccessful != null) {
					System.out.println("Referee successfully updated.");
					response.sendRedirect(request.getRequestURI()); // Redirect to refresh the page
				} else {
					System.out.println("Referee update failed.");
					response.getWriter().write("Update failed");
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				}
			} else {
				if (refereeToUpdate == null) {
					System.out.println("Referee not found with ID: " + refereeId);
					response.getWriter().write("Referee not found");
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
				if (license == null) {
					System.out.println("License ID not found: " + newLicenseId);
					response.getWriter().write("License not found");
					response.setStatus(HttpServletResponse.SC_NOT_FOUND);
				}
			}
		}

		return request.getRequestDispatcher("/register.jsp");

	}
}

