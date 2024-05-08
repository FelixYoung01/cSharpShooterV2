<%@page import="ics.ejb.Match" import="java.util.Set" import="ics.ejb.User" %>
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
	<jsp:include page="header.jsp" />
	<%
	Match match = (Match) request.getAttribute("match");
	String matchId = match.getMatchId();
	Set<User> users = (Set<User>) request.getAttribute("usersOnMatch");
	%>
	<div class="match">
		<p><%=match.getDate()%></p>
		<p><%=match.getTime()%></p>
	</div>
	<section class="Users">
	
		<h1>Users</h1>
		<%
		for (User user : users) {
			%>
			<div class="user">
			<p><%=user.getName()%></p>
			<p><%=user.getAge()%></p>
			<p><%=user.getGender()%></p>
			<p><%=user.getEmail()%></p>
			</div>
			<% 
		}
            %>
		
		</section>
		<script src="Darkmode.js"></script>
</body>
</html>