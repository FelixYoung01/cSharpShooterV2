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
			<button id="addButton">Add</button>
			<div id="addUserForm" class="popUp" style="display: none;">
				<h2>Add User</h2>
				<form id="addingUserForm" method="post">
					<label for="userId">User ID will be auto-generated!</label> 
					<br>

					<!-- Field for Name -->
					<label for="name">Name:</label> 
					<input type="text" id="name"
						name="name" required><br>

					<!-- Field for Age -->
					<label for="age">Age:</label> 
					<input type="number" id="age"
						name="age" required><br>

					<!-- Field for Email -->
					<label for="email">Email:</label> 
					<input type="email" id="email"
						name="email" required><br>

					<!-- Field for Gender -->
					<label for="gender">Gender:</label> 
					<select id="gender"
						name="gender" required>
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select><br>
					<button type="submit">Add User</button>
		
				</form>

			</div>
			<script>
			
			//lägger till eventlistener för att visa formuläret när användaren klickar på add
    		document.getElementById("addButton").addEventListener("click", function() { 
    		
    		//Gör formuläret synligt
       	 	document.getElementById("addUserForm").style.display = "block";
    		});
			//lägger till en eventlistener för att skicka formuläret
    		document.getElementById("userForm").addEventListener("submit", function(event) {
    			
        	//hindrar standardbeteendet för formuläret
        	event.preventDefault();
        
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
			<button>Add</button>
		</div>
	</div>

	<script src="Darkmode.js"></script>
</body>
</html>