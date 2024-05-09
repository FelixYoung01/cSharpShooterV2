package ics.ejb;

import java.io.IOException;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface IPathHandler {
	
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response, FacadeLocal facade) throws ServletException, IOException;

	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response, FacadeLocal facade) throws ServletException, IOException;

	
}