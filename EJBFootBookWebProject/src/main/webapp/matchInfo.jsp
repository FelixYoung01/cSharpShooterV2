<%@page import="ics.ejb.Match" import="java.util.Set"
	import="ics.ejb.User"%>
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

	Set<User> users = (Set<User>) request.getAttribute("usersOnMatch");
	%>
	<section class="matchBox">

		<p><%=match.getDate()%></p>
		<p><%=match.getTime()%></p>

		<h1>Users playing this game:</h1>
		<section class="users">
			<%
			if (users.isEmpty()) {
			%>
			<p>No users on this match, please add users on this match from
				the list bellow</p>
			<%
			}

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
		<h1>Avalible Users</h1>
		
		<%
		Set<User> avalibleUsers = (Set<User>) request.getAttribute("availableUsers");
		String selectedUser = null;
		if (avalibleUsers.isEmpty()) {
		%>
		<p>No users avalible to add to this match</p>
		<%
		}

		for (User user : avalibleUsers) {
		
		%>

		<div class="user">
			<p><%=user.getName()%></p>
			<p><%=user.getAge()%></p>
			<p><%=user.getGender()%></p>
			<p><%=user.getEmail()%></p>
			<form
				action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
				method="post">
				<input type="hidden" name="userId" value="<%=user.getUserId()%>">
				<button type="button" onclick="showButton('<%=user.getUserId()%>')">Select</button>
				
			</form>
		</div>
		<%
		}
		%>
		<form id="addUserToMatchForm"
			action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>" method="post"
			style="display: none;">
			<input type="hidden" name="userId" id="selectedUserId"> 
			<input type="hidden" name="matchId" value="<%=match.getMatchId()%>">
			<button type="submit">Add User <span id="userIdDisplay"></span> to Match</button>
		</form>
		<script>
			function showButton(userId) {
			
				
			document.getElementById("selectedUserId").value = userId;
            document.getElementById("userIdDisplay").textContent = document.getElementById("selectedUserId").value;
			document.getElementById("addUserToMatchForm").style.display = "block";
			}
		</script>
	</section>
	<script src="Darkmode.js">
		
	</script>
</body>
</html>