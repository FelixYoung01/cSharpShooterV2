package ics.ejb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.ejb.EJB;
import facade.FacadeLocal;

@WebServlet("/home")
public class FootBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final Map<String, IPathHandler> handlerMap = new HashMap<>();

	@EJB
	private FacadeLocal facade;

	public FootBookServlet() {
		super();

		handlerMap.put("/home", new HomeHandler());
		handlerMap.put("/register", new RegisterHandler());
		handlerMap.put("/pitchInfo", new PitchInfoHandler());
		handlerMap.put("/about", new AboutHandler());
		handlerMap.put("/matchInfo", new MatchInfoHandler());
		handlerMap.put("/needHelp", new NeedHelpHandler());
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String URI = request.getRequestURI();
		String pathInfo = URI.substring(path.length());

		System.out.println("$URI: " + URI);
		System.out.println("$ContextPath: " + path);
		System.out.println("$Path: " + pathInfo);

			
		IPathHandler handler = handlerMap.get(pathInfo);
		RequestDispatcher requestDispatcher = handler.handleRequestDispatcher(request, response, facade);
		requestDispatcher.forward(request, response);
	}
}