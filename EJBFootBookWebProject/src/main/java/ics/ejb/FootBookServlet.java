package ics.ejb;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.FileReader;
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
        response.setContentType("text/html");
        
        String action = request.getParameter("action");
		
        // Load the HTML content from a separate file
        String htmlFilePath = getServletContext().getRealPath("/index.html");
        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(htmlFilePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                contentBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        String fetchingData = "";
        
        if ("fetchPitchData".equals(action)) {
            fetchingData = "LET'S FETCH PITCH DATA ONE";
        }
        else if ("fetchPitchDataTwo".equals(action)) {
			fetchingData = "LET'S FETCH PITCH DATA TWO";
		}
		
		String htmlContent = contentBuilder.toString();
		htmlContent = htmlContent.replace("{{fetching_data}}", fetchingData);
		
		response.getWriter().println(htmlContent);
    }
}