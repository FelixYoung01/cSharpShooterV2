package ics.ejb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
		
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html>");
		out.println("<head><title>footbook</title>");
		out.println("<meta charset=\"ISO-8859-1\"></head>");
		out.println("<body>");
		out.println("ISPROJEKT SERVLET fungerar!");
		
		RefereeLicense rl = facade.findRefereeLicense("L1");
		
		Match m = facade.findMatch("M01");
		
		
		if (rl == null) {
			out.println("RefereeLicense not found");
			return;
		}
		else {
		out.println("<h3>RefereeLicense</h3>");
		out.println("LicenseId: " + rl.getLicenseId());
		
		out.println("<br>");
        out.println("Matchen hittades: " + m.getMatchId());
        out.println("<br>");
        out.println("Matchen spelas: " + m.getDate() + " klockan: " + m.getTime());
        out.println("<br>");
        out.println("Matchen spelas på: " + m.getPitch().getPitchId() + " " + m.getPitch().getName());
        out.println("<br>");
        
        out.println("</body></html>");	
		}
	}
		
		
		
		

}