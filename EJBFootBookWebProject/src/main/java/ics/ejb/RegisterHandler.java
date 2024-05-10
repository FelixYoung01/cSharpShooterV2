package ics.ejb;

import java.io.IOException;
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
		return request.getRequestDispatcher("/register.jsp");
	}

	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		
		Set<User> users = facade.getAllUsers();
		Set<Referee> referees = facade.getAllReferees();
		request.setAttribute("users", users);
		request.setAttribute("referees", referees);

		if (request.getMethod().equalsIgnoreCase("POST")) { // Kör kodstycket ifall metoden är POST

			String tempAge = request.getParameter("age");
			String email = request.getParameter("email");
			String gender = request.getParameter("gender");
			String name = request.getParameter("name");

			int age = Integer.parseInt(tempAge);

			User user = new User(age, email, gender, name);
			facade.createUser(user);

			response.sendRedirect(request.getRequestURI());

		}
		
		 if (request.getMethod().equalsIgnoreCase("POST")) {
		        String action = request.getParameter("action");

		        if (action != null && action.equals("remove")) {
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
		return request.getRequestDispatcher("/register.jsp");

	}
	
	
}