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
		
			if ("addUser".equals(action)){// Kör kodstycket ifall metoden är POST

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
		
			// Kör kodstycket ifall metoden är POST
			else if ("addReferee".equals(action)){
				
			String refName = request.getParameter("refereeName");
			String licenseId = request.getParameter("licenseId");

			RefereeLicense tempLicense = facade.findRefereeLicense(licenseId);
        
			Referee referee = new Referee(refName, tempLicense);
			facade.createReferee(referee);
			System.out.println("Referee added");

			response.sendRedirect(request.getRequestURI());
			}
			
			

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