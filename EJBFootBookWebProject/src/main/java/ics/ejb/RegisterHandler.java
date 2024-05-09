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
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		Set<User> users = facade.getAllUsers();
		Set<Referee> referees = facade.getAllReferees();
		request.setAttribute("users", users);
		request.setAttribute("referees", referees);
		return request.getRequestDispatcher("/register.jsp");
	}
}
