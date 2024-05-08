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
					<% Set<User> users = (Set<User>) request.getAttribute("users");
						for (User user : users) { %>
						<tr>
							<td><%= user.getUserId() %></td>
							<td><%= user.getName() %></td>
							<td><%= user.getEmail() %></td>
							<td>
								<button>Edit</button>
								<button>Remove</button>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
			<button>Add</button>
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
					<% Set<Referee> referees = (Set<Referee>) request.getAttribute("referees");
						for (Referee referee : referees) { %>
						<tr>
							<td><%= referee.getRefereeId() %></td>
							<td><%= referee.getRefereeName() %></td>
							<td><%= referee.getRefereeLicense().getLicenseId() %></td>
							<td>
								<button>Edit</button>
								<button>Remove</button>
							</td>
						</tr>
					<% } %>
				</tbody>
			</table>
			<button>Add</button>
		</div>
	</div>

	<script src="Darkmode.js"></script>
</body>
</html>