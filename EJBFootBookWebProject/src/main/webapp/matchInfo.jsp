<%@page import="ics.ejb.Match" import="java.util.Set"
	import="ics.ejb.User" import="java.time.format.DateTimeFormatter"
	import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Match Information</title>
<link rel="stylesheet" href="styles.css">

</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	Match match = (Match) request.getAttribute("match");

	Set<User> users = (Set<User>) request.getAttribute("usersOnMatch");

	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd / HH:mm");
	String createdDateFormatted = match.getCreatedDate().format(formatter);
	String lastUpdatedDateFormatted = (match.getLastUpdatedDate() != null)
			? match.getLastUpdatedDate().format(formatter)
			: "This match has not been updated";
	%>




	<div style="display: flex;">
		<section class="box colored left-aligned-text">
			<h2>Match Information</h2>
			<p>
				Match Date:
				<%=match.getDate()%></p>
			<p>
				Match Time:
				<%=match.getTime()%></p>
			<p>
				Creation Date:
				<%=createdDateFormatted%></p>
			<p>
				Last Updated:
				<%=lastUpdatedDateFormatted%></p>


		</section>
	</div>

	<section class="box colored">
		<section class="box">
			<h1 class="box rounded">Users Playing</h1>
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
				<form
					action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
					method="post">
					<input type="hidden" name="removeUserId"
						value="<%=user.getUserId()%>">
					<div class="button colored highlightable" onclick="highlightButton(this); showAddButton('<%=user.getUserId()%>')">
						<h3><%=user.getName()%></h3>
						<p><%=user.getAge()%></p>
						<p><%=user.getGender()%></p>
						<p><%=user.getEmail()%></p>
					</div>
				</form>
				<%
				}
				%>
			</div>
		</section>
		<br>
		<section class="box">
			<h1 class="box rounded">Available Users</h1>
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
					<div class="button colored highlightable"
						onclick="highlightButton(this); showAddButton('<%=user.getUserId()%>')">
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
					function showAddButton(userId) {

						document.getElementById("selectedUserId").value = userId;
						document.getElementById("userIdDisplay").textContent = userId;
						document.getElementById("addUserToMatchForm").style.display = "block";
						document.getElementById("removeUserFromMatchForm").style.display = "none";
					}
					
					function showRemoveButton(userId) {
						document.getElementById("selectedRemoveUserId").value = userId;
						document.getElementById("removeUserDisplay").textContent = userId;
						document.getElementById("removeUserFromMatchForm").style.display = "block";
						document.getElementById("addUserToMatchForm").style.display = "none";
					}
					
					function highlightButton(clickedButton) {
    // Remove highlight from all buttons
    var buttons = document.getElementsByClassName('highlightable');
    for (var i = 0; i < buttons.length; i++) {
        buttons[i].classList.remove('button-highlighted');
    }

    // Highlight the clicked button
    clickedButton.classList.add('button-highlighted');
}
				</script>
			</div>
		</section>
	</section>
	<form id="addUserToMatchForm"
		action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
		method="post" style="display: none;">
		<input type="hidden" name="formType" value="addUserToMatch">
		<input type="hidden" name="userId" id="selectedUserId"> <input
			type="hidden" name="matchId" value="<%=match.getMatchId()%>">
		<button class="colored" type="submit">
			Add User <span id="userIdDisplay"></span> To Match
		</button>
	</form>
	<form id="removeUserFromMatchForm"
		action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
		method="post" style="display: none;">
		<input type="hidden" name="formType" value="removeUserFromMatch">
		<input type="hidden" name="removeUserId" id="selectedRemoveUserId">
		<input type="hidden" name="matchId" value="<%=match.getMatchId()%>">
		<button class="colored" type="submit">
			Remove User <span id="removeUserDisplay"></span> From Match
		</button>
	</form>
	<script src="Darkmode.js">
		
	</script>
</body>
</html>