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
import java.util.ArrayList;
import java.util.List;

import jakarta.ejb.EJB;
import facade.FacadeLocal;


@WebServlet("/FootBookServlet")
public class FootBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    @EJB
	private FacadeLocal facade;
       
    public FootBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String path = request.getContextPath();
    	String URI = request.getRequestURI();
    	String pathInfo = URI.substring(path.length());
    	
    	System.out.println("URI: "+ URI);
    	System.out.println("ContextPath: " + path);
    	System.out.println("Path: " + pathInfo);
    	
    	switch (pathInfo) {
		case "/FootBookServlet":
			RequestDispatcher rd = request.getRequestDispatcher("/home.jsp");
			List<Pitch> pitches = facade.findAllPitches();
			if (pitches.isEmpty()) {
				System.out.println("No pitches found");
			}
			for (Pitch pitch : pitches) {
				            		System.out.println(pitch.getName());
			}
	    	request.setAttribute("pitches", pitches);
			rd.forward(request, response);
			
			break;
    	
		case "/register":
			RequestDispatcher rq0 = request.getRequestDispatcher("/register.jsp");
			rq0.forward(request, response);
			break;
		
		case "/about":
			RequestDispatcher rq1 = request.getRequestDispatcher("/about.jsp");
			rq1.forward(request, response);
			break;
			
		case "/pitchinfo.jsp":
			RequestDispatcher rq2 = request.getRequestDispatcher("/pitchinfo.jsp");
			rq2.forward(request, response);
			break;
    	}
    	
    	
    	
    	
    }

}