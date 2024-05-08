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

	<div class="box">
		<h2>Welcome to FootBook!</h2>
		<p>Book your football match on your favorite pitches!</p>
	</div>

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
	</section>

	<div style="margin-bottom: 100px;"></div>

	<footer>
		<p>&copy; 2024 FootBook. All rights reserved.</p>
	</footer>
	<script src="Darkmode.js"></script>
</body>
</html>
