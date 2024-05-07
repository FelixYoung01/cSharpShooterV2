<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.Pitch, java.util.Set, java.util.ArrayList"%>
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
		<button class="icon-button">
			<img src="Images/weather.webp" alt="Weather Icon" class="weather-icon">
		</button>
		<h1 class="logo">FootBook</h1>
		<header>
			<nav>
				<ul>
					<li><a href="<%=request.getContextPath()%>/home" class="button">Home</a></li>
					<li><a href="<%=request.getContextPath()%>/register" class="button">Register</a></li>
					<li><a href="<%=request.getContextPath()%>/about" class="button">About</a></li>

					<li><a href="#">Need Help?</a></li>
				</ul>
			</nav>
			<img src="Images/Arsenal_FC.svg.webp" alt="Arsenal Logo" class="arsenal">
		</header>

		<div class="box">
			<h2>Welcome to <span class="highlight">FootBook</span>!</h2>
			<p>Book your football match on your favorite pitches!</p>
		</div>

		<section class="box">
			<h2>STATISTICS HERE</h2>
		</section>

		<section class="pitches">
			<h2>Book from one of these available pitches!</h2>

			<% Set<Pitch> pitches = (Set<Pitch>) request.getAttribute("pitches");
			if (pitches != null && !pitches.isEmpty()) {
				for (Pitch pitch : pitches) {
					String pitchId = pitch.getPitchId();
			%>
			<div class="pitch">
				<h3><%=pitch.getName()%></h3>
				<a
					href="<%=request.getContextPath()%>/pitchInfo?pitchId=<%=pitchId%>" class=button>
				</a>
			</div>
			<%
			}
			} else {
			%>
			<h3>No pitches available</h3>
			<%
			}
			%>


			<% String displayTextParam = request.getParameter("displayText");
			if (displayTextParam != null && !displayTextParam.isEmpty()) { %>
				<p>COYG</p>
			<% } %>
		</section>

		<div style="margin-bottom: 50px;"></div>

		<footer>
			<p>&copy; 2024 FootBook. All rights reserved.</p>
		</footer>
	</div>
</body>
</html>
