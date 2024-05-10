<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.Pitch, java.util.Set, java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FootBook - Football Booking</title>
<link rel="stylesheet" href="styles.css">
<style>
/* Style for the weather information box */
#weatherBox {
	position: absolute;
	/* Keeps the box positioned relative to the top-left corner of the page */
	top: 10px;
	left: 10px;
	padding: 10px;
	border: 2px solid black;
	border-radius: 8px;
	background-color: #f0f0f0; /* Adjust the color as needed */
	z-index: 999; /* Ensures it's displayed above other content */
}
</style>

</head>
<body>
	<jsp:include page="header.jsp" />

	<!-- Weather Information Box -->
	<div id="weatherBox">
		<h3>Weather Information</h3>
		<p>
			City: <span id="city"></span>
		</p>
		<p>
			IP: <span id="ipNbr"></span>
		</p>
		<p>
			 <span id="sunrise"></span>
		</p>
		<p>
			 <span id="sunset"></span>
		</p>
		<p>
			Weather: <span id="weather"></span>
		</p>
		<p>
			Temperature: <span id="degree"></span>
		</p>
	</div>

	<h2>Welcome to FootBook!</h2>
	<p>Book your football match on your favorite pitches!</p>

	<section class="pitches">
		<h2>Book from one of these available pitches!</h2>

		<%
		Set<Pitch> pitches = (Set<Pitch>) request.getAttribute("pitches");
		if (pitches != null && !pitches.isEmpty()) {
			for (Pitch pitch : pitches) {
				String pitchId = pitch.getPitchId();
		%>
		<div class="pitch">
			<h3><%=pitch.getName()%></h3>
			<a
				href="<%=request.getContextPath()%>/pitchInfo?pitchId=<%=pitchId%>"
				class=button> </a>
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
	<section class="box">
		<h2>Some of our amazing statistics</h2>
		<p>Matches Registered: ${matchCount}</p>
		<p>Session Count: ${sessionCount}</p>
	</section>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="Darkmode.js"></script>
	<script src="weatherInfo.js"></script>
</body>
</html>
