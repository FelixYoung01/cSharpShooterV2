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
	<section class="box colored">

		<p><%=match.getDate()%></p>
		<p><%=match.getTime()%></p>
		<h1 class="box rounded">Users Playing</h1>
		<section class="box">
			<%
			if (users.isEmpty()) {
			%>
			<p>No users on this match, please add users on this match from
				the list below</p>
			<%
			}
			%>
			<div class="grid-container">
				<%
				for (User user : users) {
				%>
				<button class="colored">
					<h3><%=user.getName()%></h3>
					<p><%=user.getAge()%></p>
					<p><%=user.getGender()%></p>
					<p><%=user.getEmail()%></p>
				</button>
				<%
				}
				%>
			</div>
		</section><br>
		<h1 class="box rounded">Available Users</h1>
		<section class="box">
			<div class="grid-container">
				<%
				Set<User> availableUsers = (Set<User>) request.getAttribute("availableUsers");
				String selectedUser = null;
				if (availableUsers.isEmpty()) {
				%>
				<p>No users available to add to this match</p>
				<%
				}

				for (User user : availableUsers) {
				%>

				<form
					action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
					method="post">
					<input type="hidden" name="userId" value="<%=user.getUserId()%>">
					<div class="button colored" onclick="showButton('<%=user.getUserId()%>')">
						<p><%=user.getName()%></p>
						<p><%=user.getAge()%></p>
						<p><%=user.getGender()%></p>
						<p><%=user.getEmail()%></p>
					</div>
				</form>
				<%
				}
				%>
				<script>
					function showButton(userId) {

						document.getElementById("selectedUserId").value = userId;
						document.getElementById("userIdDisplay").textContent = userId;
						document.getElementById("addUserToMatchForm").style.display = "block";
					}
				</script>
			</div>
		</section>
	</section>
	<form id="addUserToMatchForm"
		action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
		method="post" style="display: none;">
		<input type="hidden" name="userId" id="selectedUserId"> <input
			type="hidden" name="matchId" value="<%=match.getMatchId()%>">
		<button class="colored" type="submit">
			Add User <span id="userIdDisplay"></span> to Match
		</button>
	</form>
	<script src="Darkmode.js">
		
	</script>
</body>
</html>