package ics.ejb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class FootBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FootBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>footbook</title>");
		out.println("<meta charset=\"ISO-8859-1\"></head>");
		out.println("<body>");
		out.println("ISPROJEKT SERVLET fungerar!");
		out.println("</body></html>");	}

}