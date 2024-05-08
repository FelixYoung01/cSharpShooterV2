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

	
	private static final String FOOT_BOOK_SERVLET_PATH = "/home";
	private static final String REGISTER_PATH = "/register";
	private static final String PITCH_INFO_PATH = "/pitchInfo";
	private static final String ABOUT_PATH = "/about";
	private static final String MATCH_INFO_PATH = "/matchId";
	private static final String NEED_HELP_PATH = "/needHelp";


	@EJB
	private FacadeLocal facade;

	public FootBookServlet() {
		super();

		handlerMap.put(FOOT_BOOK_SERVLET_PATH, new HomeHandler());
		handlerMap.put(REGISTER_PATH, new RegisterHandler());
		handlerMap.put(PITCH_INFO_PATH, new PitchInfoHandler());
		handlerMap.put(ABOUT_PATH, new AboutHandler());
		handlerMap.put(MATCH_INFO_PATH, new MatchInfoHandler());
		handlerMap.put(NEED_HELP_PATH, new NeedHelpHandler());

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