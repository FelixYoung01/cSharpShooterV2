<%@page import="ics.ejb.Match"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
	Match match = (Match) request.getAttribute("match");
	String matchId = match.getMatchId();
	%>
	<div class="match">
		<p><%=match.getDate()%></p>
		<p><%=match.getTime()%></p>
	</div>
	<script src="Darkmode.js"></script>
</body>
</html>