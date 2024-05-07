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
import java.util.List;

import jakarta.ejb.EJB;
import facade.FacadeLocal;

@WebServlet("/home")
public class FootBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String FOOT_BOOK_SERVLET_PATH = "/home";
	private static final String REGISTER_PATH = "/register";
	private static final String PITCH_INFO_PATH = "/pitchInfo";
	private static final String ABOUT_PATH = "/about";

	@EJB
	private FacadeLocal facade;

	public FootBookServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getContextPath();
		String URI = request.getRequestURI();
		String pathInfo = URI.substring(path.length());

		System.out.println("$URI: " + URI);
		System.out.println("$ContextPath: " + path);
		System.out.println("$Path: " + pathInfo);

		// Call the facade method to get the data
		Set<Pitch> pitches = facade.getAllPitches();

		// Set the data as a request attribute
		request.setAttribute("pitches", pitches);

			
		// Kanske ändra till strategy design pattern för att hantera olika paths.
		switch (pathInfo) {
		case FOOT_BOOK_SERVLET_PATH:
			RequestDispatcher r1 = request.getRequestDispatcher("/home.jsp");
			r1.forward(request, response);
			break;

		case REGISTER_PATH:
			RequestDispatcher r2 = request.getRequestDispatcher("/register.jsp");
			r2.forward(request, response);
			break;

		case PITCH_INFO_PATH:
			
			String pitchId = request.getParameter("pitchId");
			Pitch pitch = facade.findPitch(pitchId);
			
			request.setAttribute("pitch", pitch);
			
			RequestDispatcher r3 = request.getRequestDispatcher("/pitchInfo.jsp");
			r3.forward(request, response);
			break;

		case ABOUT_PATH:
			RequestDispatcher r4 = request.getRequestDispatcher("/about.jsp");
			r4.forward(request, response);
			break;

		default:
			System.out.println("Invalid path: " + pathInfo);
			// Optionally forward to an error page
			// RequestDispatcher errorDispatcher =
			// request.getRequestDispatcher("/error.jsp");
			// errorDispatcher.forward(request, response);
			break;
		}
	}
}