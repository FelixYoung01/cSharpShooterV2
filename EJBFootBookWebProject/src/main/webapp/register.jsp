<%@page import="ics.ejb.RefereeLicense"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.User, ics.ejb.Referee, java.util.Set"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Register - FootBook</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<h1 style="font-size: 60px;">Register</h1>
	<p>Register users and referees here.</p>

	<div style="display: flex;">
		<div>
			<h2>Users</h2>
			<button class="bigger-button" id="addUserButton">Add User</button>
			<table class="box colored">
				<thead>
					<tr>
						<th>ID</th>
						<th>Name</th>
						<th>Email</th>
						<th>Gender</th>
						<th>Age</th>
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
						<td><%=user.getGender()%></td>
						<td><%=user.getAge()%></td>
						<td>
							<button
								onclick="editUser('<%=user.getUserId()%>', '<%=user.getName()%>', '<%=user.getAge()%>', '<%=user.getEmail()%>', '<%=user.getGender()%>')">Edit</button>
							<form action="/EJBFootBookWebProject/register" method="post"
								style="display: inline;">
								<input type="hidden" name="formType" value="removeUser">
								<input type="hidden" name="userId" value="<%=user.getUserId()%>">
								<button type="submit"
									onclick="return confirm('Are you sure you want to remove this user? This action can not be undone.')">Remove</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<div id="addUserForm" class="overlay" style="display: none;">
				<div class="modalContainer">
					<div class="box colored" id="addUserBox">
						<h2>Add User</h2>
						<form id="addingUserForm" action="/EJBFootBookWebProject/register"
							name="userFormType" method="post">
							<label for="userId">User ID will be auto-generated!</label> <br>
							<br> <label for="userName">Name:</label>
							<div style="display: flex; justify-content: center;">
								<input type="text" class="bordered-input" id="userName"
									name="userName" required>
							</div>
							<label for="userAge">Age:</label>
							<div style="display: flex; justify-content: center;">
								<input type="number" class="bordered-input" id="userAge"
									name="userAge" required min="18" max="100">
							</div>
							<label for="userEmail">Email:</label>
							<div style="display: flex; justify-content: center;">
								<input type="email" class="bordered-input" id="userEmail"
									name="userEmail" required>
							</div>
							<label for="userGender">Gender:</label>
							<div style="display: flex; justify-content: center;">
								<select class="bordered-input" name="userGender" required>
									<option value="M">Male</option>
									<option value="F">Female</option>
								</select>
							</div>
							<br> <input type="hidden" name="formType" value="addUser">
							<button type="submit">Add User</button>
							<button type="button" onclick="hideModal()">Close</button>
						</form>
					</div>
				</div>
			</div>

			<div id="editUserForm" class="overlay" style="display: none;">
				<div class="modalContainer">
					<div class="box colored" id="editUserBox">
						<h2>Edit User</h2>
						<form id="editingUserForm"
							action="/EJBFootBookWebProject/register" method="post">
							<input type="hidden" name="formType" value="editUser"> <input
								type="hidden" id="editUserId" name="userId" required>
							<div>
								<label>User ID:</label> <span id="displayUserId"></span>
							</div>
							<label for="editUserName">Name:</label><br> <input
								type="text" class="bordered-input" id="editUserName"
								name="userName" required><br> <label
								for="editUserAge">Age:</label><br> <input type="number"
								class="bordered-input" id="editUserAge" name="userAge" required
								min="18" max="100"><br> <label for="editUserEmail">Email:</label><br>
							<input type="email" class="bordered-input" id="editUserEmail"
								name="userEmail" required><br> <label
								for="editUserGender">Gender:</label><br> <select
								class="bordered-input" id="editUserGender" name="userGender"
								required>
								<option value="M">Male</option>
								<option value="F">Female</option>
							</select><br>
							<button type="submit">Update User</button>
							<button type="button" onclick="hideModal()">Close</button>
						</form>
					</div>
				</div>
			</div>

			<script>
				var currentBox;

				function editUser(userId, name, age, email, gender) {
					document.getElementById('editUserId').value = userId;
					document.getElementById('displayUserId').innerText = userId; // Display user ID but not editable
					document.getElementById('editUserName').value = name;
					document.getElementById('editUserAge').value = age;
					document.getElementById('editUserEmail').value = email;
					document.getElementById('editUserGender').value = gender;

					document.getElementById('editUserForm').style.display = 'block';
					document.getElementById('editUserBox').classList
							.add('pop-up');
					currentBox = document.getElementById('editUserBox');
				}

				// Event listener for showing add user form
				document
						.getElementById("addUserButton")
						.addEventListener(
								"click",
								function() {
									document.getElementById("addUserForm").style.display = "block";
									document.getElementById('addUserBox').classList
											.add('pop-up');
									currentBox = document
											.getElementById('addUserBox');
								});

				// Event listener for hiding add user form after submission
				document
						.getElementById("addingUserForm")
						.addEventListener(
								"submit",
								function(event) {
									document.getElementById("addUserForm").style.display = "none";
								});
			</script>
		</div>

		<div style="margin-left: 20px;">
			<h2>Referees</h2>
			<button class="bigger-button" id="addRefereeButton">Add
				Referee</button>
			<table class="box colored">
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
							<button
								onclick="editReferee('<%=referee.getRefereeId()%>', '<%=referee.getRefereeName()%>', '<%=referee.getRefereeLicense().getLicenseId()%>')">Edit</button>
							<form action="/EJBFootBookWebProject/register" method="post"
								style="display: inline;">
								<input type="hidden" name="formType" value="removeReferee">
								<input type="hidden" name="refereeId"
									value="<%=referee.getRefereeId()%>">
								<button type="submit"
									onclick="return confirm('Are you sure you want to remove this referee?')">Remove</button>
							</form>
						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<div id="addRefereeForm" class="overlay" style="display: none;">
				<div class="modalContainer">
					<div class="box colored" id="addRefereeBox">
						<h2>Add Referee</h2>
						<form id="addingRefereeForm"
							action="/EJBFootBookWebProject/register" name="refereeFormType"
							method="post">
							<label for="RefereeId">Referee ID will be auto-generated!</label>
							<br> <br> <label for="refereeName">Name:</label>
							<div style="display: flex; justify-content: center;">
								<input type="text" class="bordered-input" id="refereeName"
									name="refereeName" required>
							</div>
							<label for="licenseId">License:</label>
							<div style="display: flex; justify-content: center;">
								<select class="bordered-input" name="licenseId" required>
									<option value="" selected disabled>Select License</option>
									<%
									List<RefereeLicense> licenses = (List<RefereeLicense>) request.getAttribute("licenses");
									if (licenses == null) {
										System.out.println("licenses is null");
									} else {
										for (RefereeLicense license : licenses) {
									%>
									<option value="<%=license.getLicenseId()%>"><%=license.getLicenseId()%></option>
									<%
									}
									}
									%>
								</select>
							</div>
							<br> <input type="hidden" name="formType" value="addReferee">
							<button type="submit">Add Referee</button>
							<button type="button" onclick="hideModal()">Close</button>
						</form>
					</div>
				</div>
			</div>


			<div id="editRefereeForm" class="overlay" style="display: none;">
				<div class="modalContainer">
					<div class="box colored" id="editRefereeBox">
						<h2>Edit Referee</h2>
						<form id="editingRefereeForm"
							action="/EJBFootBookWebProject/register" method="post">
							<input type="hidden" name="formType" value="editReferee">
							<input type="hidden" id="editRefereeId" name="refereeId" required>
							<label for="displayRefereeId">Referee ID:</label><br> <input
								type="text" class="bordered-input" id="displayRefereeId"
								value="" disabled><br> <label for="editRefereeName">Name:</label><br>
							<input type="text" id="editRefereeName" class="bordered-input"
								name="refereeName" required><br> <label
								for="editRefereeLicense">License:</label><br> <select
								class="bordered-input" id="editRefereeLicense" name="licenseId"
								required>
								<%
								for (RefereeLicense license : licenses) {
								%>
								<option value="<%=license.getLicenseId()%>"><%=license.getLicenseId()%></option>
								<%
								}
								%>
							</select><br>
							<button type="submit">Update Referee</button>
							<button type="button" onclick="hideModal()">Close</button>
						</form>
					</div>
				</div>
			</div>

			<script>
				function editReferee(refereeId, refereeName, licenseId) {
					document.getElementById('editRefereeId').value = refereeId;
					document.getElementById('displayRefereeId').value = refereeId;
					document.getElementById('editRefereeName').value = refereeName;
					document.getElementById('editRefereeLicense').value = licenseId;

					document.getElementById('editRefereeForm').style.display = 'block';
					document.getElementById('editRefereeBox').classList
							.add('pop-up');
					currentBox = document.getElementById('editRefereeBox');
				}

				// Event listener for showing add referee form
				document
						.getElementById("addRefereeButton")
						.addEventListener(
								"click",
								function() {
									document.getElementById("addRefereeForm").style.display = "block";
									document.getElementById('addRefereeBox').classList
											.add('pop-up');
									currentBox = document
											.getElementById('addRefereeBox');
								});

				// Event listener for hiding add referee form after submission
				document
						.getElementById("addingRefereeForm")
						.addEventListener(
								"submit",
								function(event) {
									document.getElementById("addRefereeForm").style.display = "none";
								});
			</script>

			<script>
				function hideModal() {
					currentBox.classList.remove("pop-up");
					currentBox.classList.add("pop-down");
					setTimeout(
							function() {
								document.getElementById('addUserForm').style.display = 'none';
								document.getElementById('editUserForm').style.display = 'none';
								document.getElementById('addRefereeForm').style.display = 'none';
								document.getElementById('editRefereeForm').style.display = 'none';
								currentBox.classList.remove("pop-down");
							}, 300);
				}
			</script>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

	<script src="Darkmode.js"></script>
	<script src="weatherInfo.js"></script>

</body>
</html>
