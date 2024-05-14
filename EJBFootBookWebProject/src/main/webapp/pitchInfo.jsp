
<%@page import="java.util.Map" import="ics.ejb.Pitch"
	import="java.util.Set" import="ics.ejb.Match"
	import="java.time.format.DateTimeFormatter"
	import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pitch Information</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	Pitch pitch = (Pitch) request.getAttribute("pitch");
	String pitchId = pitch.getPitchId();

	// Define the date formatter
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Format the founded date
	String foundedDateFormatted = (pitch.getFounded() != null) ? pitch.getFounded().format(formatter) : "Not Available";
	%>
	<section class="box colored">
		<h1>Pitch Information</h1>
		<div>
			<h3>
				Pitch Name:
				<%=pitch.getName()%></h3>
			<p>
				Pitch ID:
				<%=pitchId%></p>
			<p>
				Founded:
				<%=foundedDateFormatted%></p>

			<img src="Images/<%=pitch.getImageName()%>" alt="Pitch Image"
				class="bordered-image" style="width: 800px;" />

		</div>

		<h1>Matches on this pitch</h1>
		<section class="grid-container">
			<%
			Set<Match> matches = (Set<Match>) request.getAttribute("matchesOnPitch");
			Map<String, Integer> matchUserCounts = (Map<String, Integer>) request.getAttribute("matchUserCount");
			for (Match match : matches) {
			%>
			<a class="button"
				href="<%=request.getContextPath()%>/matchInfo?matchId=<%=match.getMatchId()%>">
				<h3><%=match.getMatchId()%></h3>
				<p>
					Match Date:
					<%=match.getDate()%>
				</p>
				<p>
					Match Time:
					<%=match.getTime()%>
				</p>
				<p>
					Number of players:
					<%=matchUserCounts.get(match.getMatchId())%>
				</p>
			</a>
			<%
			}
			%>
		</section>
	</section>
	<script src="Darkmode.js"></script>
</body>
</html>