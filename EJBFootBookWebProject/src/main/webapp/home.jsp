<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.Pitch, java.util.Set, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FootBook - Football Booking</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="background"></div>
    <div class="content">
    <img src="Images/weather.webp" alt="Weather Icon" class="weather">
    <img src="Images/footbooklogo.png" alt="FootBook Logo" class="logo">
    <header>
        <nav>
            <ul>
                <li><a href="<%=request.getContextPath()%>/home" class=button>Home</a></li>
                <li><a href="<%=request.getContextPath()%>/register" class=button >Register</a></li>
					      <li><a href="<%=request.getContextPath()%>/about" class=button>About</a></li>
					        <li><a href="#">Need Help?</a></li>
				    </ul>
			  </nav>
			  <img src="Images/Arsenal_FC.svg.webp" alt="Arsenal Logo" class="arsenal">
		</header>

		<div class="box">
			<h2>
				Welcome to <span class="highlight">FootBook</span>!
			</h2>
			<p>Book your football match on your favorite pitches!</p>
		</div>

		<section class="box">
			<h2>STATISTICS HERE</h2>
		</section>

    <section class="pitches">
        <h2>Book from one of these available pitches</h2>
        <%
	  Set<Pitch> pitches = (Set<Pitch>) request.getAttribute("pitches");
	  if (pitches != null && !pitches.isEmpty()) {
	    for (Pitch pitch : pitches) {
        %>
            <div class="pitch">
                <h3><%= pitch.getName() %></h3>
                <form action="<%=request.getContextPath()%>/pitchInfo" method="post">
                    <button type="submit" name="displayText" value="true"></button>
                </form>
            </div>
        <%
		    }
		} else {
		%>
		    <h3>No pitches available</h3>
		<%
		}
		%>

    <% 
    	String displayTextParam = request.getParameter("displayText");
    	if (displayTextParam != null && !displayTextParam.isEmpty()) {
	%>
    <p>COYG</p>
	<%
		}
	%>
    </section>
    
    <div style="margin-bottom: 50px;"></div>

		<section class="pitches">
			<h2>Book from one of these available pitches</h2>
			<div class="pitch">
				<tables>
				<tr>
					<th>PitchName</th>
					<th>PitchId</th>

					<%
					ArrayList<ics.ejb.Pitch> pitches = (ArrayList<ics.ejb.Pitch>) request.getAttribute("pitches");
					for (ics.ejb.Pitch pitch : pitches) {
						System.out.println(pitch.getName());
					%>
				
				<tr>
					<td><%=pitch.getName()%></td>
					<td><%=pitch.getPitchId()%></td>
					
				</tr>
				<%
				}
				%> 
				</tables>
			</div>
			<%
			String displayTextParam = request.getParameter("displayText");
			if (displayTextParam != null && !displayTextParam.isEmpty()) {
			%>
			<p>COYG</p>
			<%
			}
			%>
		</section>

		<footer>
			<p>&copy; 2024 FootBook. All rights reserved.</p>
		</footer>
	</div>
</body>
</html>