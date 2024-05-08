package ics.ejb;

import java.io.IOException;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RegisterHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcher(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
		return request.getRequestDispatcher("/register.jsp");
	}
}
