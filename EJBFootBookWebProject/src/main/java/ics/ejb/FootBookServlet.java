package ics.ejb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jakarta.ejb.EJB;
import facade.FacadeLocal;
import ics.exceptions.FootBookException;

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
        String pathInfo = request.getRequestURI().substring(request.getContextPath().length());
        System.out.println("$doGetPath: " + pathInfo);
        IPathHandler handler = handlerMap.get(pathInfo);
        RequestDispatcher requestDispatcher;
        if (pathInfo.equals("/home")) {
			try {
				requestDispatcher = handler.handleRequestDispatcherGet(request, response, facade);
	            if (requestDispatcher != null) {
	                requestDispatcher.forward(request, response);
	            }
			} catch (ServletException | IOException | FootBookException e) {
				System.out.println("Error: " + e.getMessage());
				// Redirect to error.jsp and show error message
		        request.setAttribute("errorMessage", e.getMessage());
		        RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
		        errorDispatcher.forward(request, response);
                
			}
        } else {
            doPost(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String path = request.getContextPath();
        String URI = request.getRequestURI();
        String pathInfo = URI.substring(path.length());

        System.out.println("$URI: " + URI);
        System.out.println("$ContextPath: " + path);
        System.out.println("$doPostPath: " + pathInfo);

        IPathHandler handler = handlerMap.get(pathInfo);

        RequestDispatcher requestDispatcher;
		try {
			requestDispatcher = handler.handleRequestDispatcherPost(request, response, facade);
	        if (response.isCommitted()) {
	            return;
	        }
	        if (requestDispatcher != null) {
	            requestDispatcher.forward(request, response);
	        }
		} catch (ServletException | IOException | FootBookException e) {
			System.out.println("Error: " + e.getMessage());
			// Redirect to error.jsp and show error message
	        request.setAttribute("errorMessage", e.getMessage());
	        RequestDispatcher errorDispatcher = request.getRequestDispatcher("/error.jsp");
	        errorDispatcher.forward(request, response);
		}
    }

    public void init() throws ServletException {
        getServletContext().setAttribute("sessionCount", 0);
    }
}
