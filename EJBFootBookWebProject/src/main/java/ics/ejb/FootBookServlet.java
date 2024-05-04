package ics.ejb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import jakarta.ejb.EJB;
import facade.FacadeLocal;


@WebServlet("/FootBookServlet")
public class FootBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/*@EJB
	private FacadeLocal facade;*/
       
    public FootBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	request.getRequestDispatcher("/home.jsp").forward(request, response);
    }
}