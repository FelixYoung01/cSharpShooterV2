package ics.ejb;

import java.io.IOException;

import facade.FacadeLocal;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class NeedHelpHandler implements IPathHandler {

	@Override
	public RequestDispatcher handleRequestDispatcherGet(HttpServletRequest request, HttpServletResponse response,
			FacadeLocal facade) throws ServletException, IOException {
        return request.getRequestDispatcher("/needHelp.jsp");
	}
	
	@Override
	public RequestDispatcher handleRequestDispatcherPost(HttpServletRequest request, HttpServletResponse response,
            FacadeLocal facade) throws ServletException, IOException {
		// If send message button was pressed, add message to database using facade
		
		String action = request.getParameter("formType");
		if ("sendMessage".equals(action)) {
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String message = request.getParameter("message");

			UserMessage userMessage = new UserMessage(name, email, message);
			
			facade.addUserMessage(userMessage);
		}
		
		response.sendRedirect(request.getContextPath() + "/needHelp.jsp");
		return null;
    }
}


