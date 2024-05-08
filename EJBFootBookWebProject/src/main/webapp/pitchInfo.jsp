
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="ics.ejb.Pitch" import="java.util.Set" import="ics.ejb.Match"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
<jsp:include page="header.jsp" />
	<%
	Pitch pitch = (Pitch) request.getAttribute("pitch");
	String pitchId = pitch.getPitchId();
	%>
	<h1>Information about pitch</h1>
	<div>
		<h3>
			Pitch Name:
			<%=pitch.getName()%></h3>
		<p>
			Pitch ID:
			<%=pitchId%></p>
	</div>
	<section class="Matches">
		<%
		Set<Match> matches = (Set<Match>) request.getAttribute("matchesOnPitch");
		for (Match match : matches) {
		%>
		<div class="match">
			<h3><%=match.getMatchId()%></h3>
			<a
				href="<%=request.getContextPath()%>/pitchInfo?pitchId=<%=pitchId%>&matchId=<%=match.getMatchId()%>">
			</a>
			<p>
				Match Date:
				<%=match.getDate()%></p>
			<p>
				Match Time:
				<%=match.getTime()%></p>
			<%
			}
			%>
		</div>
	</section>
	<script src="Darkmode.js"></script>
</body>
</html>