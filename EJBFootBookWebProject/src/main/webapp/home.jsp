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

</head>
<body>
	<jsp:include page="header.jsp" />

	<h2 style="font-size: 60px">Welcome to FootBook!</h2>
	<p>Book your football match on your favorite pitches!</p>

	<section class="pitches">
		<h2>Book from one of these available pitches!</h2>

		<%
		Set<Pitch> pitches = (Set<Pitch>) request.getAttribute("pitches");
		if (pitches != null && !pitches.isEmpty()) {
			for (Pitch pitch : pitches) {
				String pitchId = pitch.getPitchId();
				String imagePath = "Images/" + pitch.getImageName();
		%>
		
		<a class="a-button" href="<%=request.getContextPath()%>/pitchInfo?pitchId=<%=pitchId%>">
			<div id="pitch-label"><%=pitch.getName()%></div>
			<img src="<%=imagePath%>" class="pitch-image" alt="Pitch Image"/>
		</a>

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
		<p>Users Registered: ${userCount}</p>
		<p>Users Registered in Matches: ${userOnMatchesCount}</p>
		<p>Session Count: ${sessionCount}</p>

	</section>
	
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="Darkmode.js"></script>
	<script src="weatherInfo.js"></script>
</body>
</html>
