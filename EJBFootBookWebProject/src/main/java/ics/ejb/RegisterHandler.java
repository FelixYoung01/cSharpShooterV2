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

		if ("addUser".equals(action)) {
			String tempAge = request.getParameter("userAge");
			String email = request.getParameter("userEmail");
			String gender = request.getParameter("userGender");
			String name = request.getParameter("userName");
			int age = Integer.parseInt(tempAge);

			User user = new User(age, email, gender, name);
			facade.createUser(user);

			System.out.println("User added");
			response.sendRedirect(request.getRequestURI());
		} else if ("addReferee".equals(action)) {
			String refName = request.getParameter("refereeName");
			String licenseId = request.getParameter("licenseId");

			RefereeLicense tempLicense = facade.findRefereeLicense(licenseId);
			Referee referee = new Referee(refName, tempLicense);
			facade.createReferee(referee);

			System.out.println("Referee added");
			response.sendRedirect(request.getRequestURI());
		} else if ("editUser".equals(action)) {
			String userId = request.getParameter("userId");
			String newName = request.getParameter("userName");
			String newAge = request.getParameter("userAge");
			String newEmail = request.getParameter("userEmail");
			String newGender = request.getParameter("userGender");

			User userToUpdate = facade.findUserById(userId); // Assume this is the correct method name

			if (userToUpdate != null) {
				int age = Integer.parseInt(newAge);
				userToUpdate.setName(newName);
				userToUpdate.setAge(age);
				userToUpdate.setEmail(newEmail);
				userToUpdate.setGender(newGender);

				facade.updateUser(userToUpdate);

				System.out.println("User updated");
				response.sendRedirect(request.getRequestURI());
			} else {
				response.getWriter().write("User not found");
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else if ("removeUser".equals(action)) {
			String userId = request.getParameter("userId");
			User user = facade.findUser(userId);

			if (user != null) {
				facade.deleteUser(userId);
				System.out.println("User removed: " + userId);
			} else {
				System.out.println("User not found: " + userId);
			}


			response.sendRedirect(request.getRequestURI());
		}


		return request.getRequestDispatcher("/register.jsp");
	}
}
