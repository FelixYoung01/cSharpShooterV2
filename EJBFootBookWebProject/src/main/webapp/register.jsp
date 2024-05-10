<%@page import="ics.ejb.RefereeLicense"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.User, ics.ejb.Referee, java.util.Set"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>Register</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div style="display: flex;">
		<div>
			<h2>Users</h2>
			<table class="box">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
					Set<User> users = (Set<User>) request.getAttribute("users");
								for (User user : users) {
					%>
					<tr>
						<td><%=user.getUserId()%></td>
						<td><%=user.getName()%></td>
						<td><%=user.getEmail()%></td>
						<td>
							<button>Edit</button>

						<td>
    <button onclick="removeUser('<%=user.getUserId()%>')">Remove</button>
</td>
							
							
							
							
							
						</td>
						
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<button id="addUserButton">Add</button>
			<div id="addUserForm" class="popUp" style="display: none;">
				<h2>Add User</h2>
				<form id="addingUserForm" action="/EJBFootBookWebProject/register" name="userFormType"
					method="post">
					<label for="userId">User ID will be auto-generated!</label> <br>

					<!-- Field for Name -->
					<label for="userName">Name:</label> <input type="text" id="userName"
						name="userName" required><br>

					<!-- Field for Age -->
					<label for="userAge">Age:</label> <input type="number" id="userAge"
						name="userAge" required><br>

					<!-- Field for Email -->
					<label for="userEmail">Email:</label> <input type="email" id="userEmail"
						name="userEmail" required><br>

					<!-- Field for Gender -->
					<label for="userGender">Gender:</label> <select id="userGender"
						name="userGender" required>
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select><br>
					<input type="hidden" name="formType" value="addUser">
					<button type="submit">Add User</button>

				</form>

			</div>
			<script>
				//lägger till eventlistener för att visa formuläret när användaren klickar på add
				document
						.getElementById("addUserButton")
						.addEventListener(
								"click",
								function() {

									//Gör formuläret synligt
									document.getElementById("addUserForm").style.display = "block";
								});
				//lägger till en eventlistener för att skicka formuläret
				document
						.getElementById("userForm")
						.addEventListener(
								"submitUser",
								function(event) {

									//hindrar standardbeteendet för formuläret
									

									//Gömmer formuläret igen
									document.getElementById("addUserForm").style.display = "none";
								});
			</script>
		</div>

		<div style="margin-left: 20px;">
			<h2>Referees</h2>
			<table class="box">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>License ID</th>
						<th>Actions</th>
					</tr>
				</thead>
				<tbody>
					<%
					Set<Referee> referees = (Set<Referee>) request.getAttribute("referees");
					for (Referee referee : referees) {
					%>
					<tr>
						<td><%=referee.getRefereeId()%></td>
						<td><%=referee.getRefereeName()%></td>
						<td><%=referee.getRefereeLicense().getLicenseId()%></td>
						<td>
							<button>Edit</button>
							<button>Remove</button>
							
							
							
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<button id="addRefereeButton">Add</button>
			<div id="addRefereeForm" class="popUp" style="display: none;">
				<h2>Add Referee</h2>
				<form id="addingRefereeForm" action="/EJBFootBookWebProject/register" name="refereeFormType" 
					method="post">
					<label for="RefereeId">RefereeId kommer!</label> <br>

					<!-- Field for Name -->
					<label for="refereeName">Name:</label> <input type="text" id="refereeName"
						name="refereeName" required><br> 
						
						<label for="licenseId">Licence:</label>
					<select id="licenseId" name="licenseId" required>
						<option value="">Select Licence</option>

						<%
						List<RefereeLicense> licences = (List<RefereeLicense>) request.getAttribute("licenses");
						
						if (licences == null){
							System.out.println("licences is null");
						}
						else {
						for (RefereeLicense licence : licences) {
						%>
						<option value="<%=licence.getLicenseId()%>"><%=licence.getLicenseId()%></option>
						<%
						}}
						%>
					</select><br>
					<input type="hidden" name="formType" value="addReferee">
					<button type="submit">Submit</button>
				</form>

			</div>
			<script>
				//lägger till eventlistener för att visa formuläret när användaren klickar på add
				document
						.getElementById("addRefereeButton")
						.addEventListener(
								"click",
								function() {

									//Gör formuläret synligt
									document.getElementById("addRefereeForm").style.display = "block";
								});
				//lägger till en eventlistener för att skicka formuläret
				document
						.getElementById("addRefereeForm")
						.addEventListener(
								"submit",
								function(event) {

									//hindrar standardbeteendet för formuläret
									

									//Gömmer formuläret igen
									document.getElementById("addRefereeForm").style.display = "none";
								});
			</script>
		</div>
	</div>

	<script src="Darkmode.js"></script>
</body>
</html>