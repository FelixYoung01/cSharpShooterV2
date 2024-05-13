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
						<td><%=user.getGender() %> </td>
						<td><%=user.getAge() %> </td>
						
						<td>

							<button id="editUserButton">UserEdit</button>
						<td>
							<button onclick="removeUser('<%=user.getUserId()%>')">Remove</button>
						</td>





						</td>

							<button>Edit</button>
							
			                <button class="removeButton" data-userId="<%= user.getUserId() %>">Remove</button>	
			                			
							<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>

<script>
$(document).ready(function() {
    // Event listener for 'Remove' buttons
    $('.removeButton').click(function() {
        var userId = $(this).data('user-id');
        
        // Confirm user removal
        if (confirm('Are you sure you want to remove this user?')) {
            // AJAX request to servlet for user removal
            $.post('RegisterHandler', { action: 'removeUser', userId: userId })
                .done(function(response) {
                    // Remove the corresponding row from the table
                    $('tr').has('td:contains("' + userId + '")').remove();
                    alert('User removed successfully!');
                })
                .fail(function(xhr, status, error) {
                    alert('Failed to remove user. Please try again.');
                });
        }
    });
});
</script>
							
							
							
						</td>
						
						
				
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<button id="addUserButton">Add</button>
			<div id="addUserForm" class="box popUp" style="display: none;">
				<h2>Add User</h2>

				<form id="addingUserForm" action="/EJBFootBookWebProject/register" name="userFormType" method="post">
					<label for="userId">User ID will be auto-generated!</label> <br><br>
			
					<!-- Field for Name -->
					<label for="userName">Name:</label> 
					<div style="display: flex; justify-content: center;">
						<input type="text" class="bordered-input" id="userName" name="userName" required>
					</div>
			
					<!-- Field for Age -->
					<label for="userAge">Age:</label> 
					<div style="display: flex; justify-content: center;">
						<input type="number" class="bordered-input" id="userAge" name="userAge" required>
					</div>
			
					

					
			
			<div id="editUserForm" class="popUp" style="display: none;">
				<h2>Edit User</h2>
				<form id="editingUserForm" action="/EJBFootBookWebProject/register"
					method="post">
					<!-- Hidden Field for Form Type -->
					<input type="hidden" name="formType" value="editUser">

					<!-- Hidden Field for User ID (non-editable) -->
					<input type="hidden" id="editUserId" name="userId" required>

					<!-- Display User ID (non-editable) -->
					<div>
						<label>User ID:</label> <span id="displayUserId"></span>
					</div>

					<!-- Field for Name -->
					<label for="editUserName">Name:</label> <input type="text"
						id="editUserName" name="userName" required><br>

					<!-- Field for Age -->
					<label for="editUserAge">Age:</label> <input type="number"
						id="editUserAge" name="userAge" required><br>

					<!-- Field for Email -->
					<label for="editUserEmail">Email:</label> <input type="email"
						id="editUserEmail" name="userEmail" required><br>

					<!-- Field for Gender -->
					<label for="editUserGender">Gender:</label> <select
						id="editUserGender" name="userGender" required>
						<option value="M">Male</option>
						<option value="F">Female</option>
					</select><br>

					<button type="submit">Update User</button>
				</form>
        <!-- Field for Email -->
					<label for="userEmail">Email:</label> 
					<div style="display: flex; justify-content: center;">
						<input type="email" class="bordered-input" id="userEmail" name="userEmail" required>
					</div>
			
					<!-- Field for Gender -->
					<label for="userGender">Gender:</label> 
					<div style="display: flex; justify-content: center;">
						<select id="userGender" name="userGender" required>
							<option value="M">Male</option>
							<option value="F">Female</option>
						</select>
					</div><br>
					<input type="hidden" name="formType" value="addUser">
					<button type="submit">Submit</button>
				</form>
			</div>


			<script>
				// Function to update the user details
				function editUser(userId, name, age, email, gender) {
					document.getElementById('editUserId').value = userId;
					document.getElementById('displayUserId').innerText = userId; // Display user ID but not editable
					document.getElementById('editUserName').value = name;
					document.getElementById('editUserAge').value = age;
					document.getElementById('editUserEmail').value = email;
					document.getElementById('editUserGender').value = gender;

					document.getElementById('editUserForm').style.display = 'block';
				}
			</script>

			<script>
				//l�gger till eventlistener f�r att visa formul�ret n�r anv�ndaren klickar p� add
				document
						.getElementById("addUserButton")
						.addEventListener(
								"click",
								function() {

									//G�r formul�ret synligt
									document.getElementById("addUserForm").style.display = "block";
								});
				//l�gger till en eventlistener f�r att skicka formul�ret
				document
						.getElementById("userForm")
						.addEventListener(
								"submitUser",
								function(event) {


									//hindrar standardbeteendet f�r formul�ret

									//G�mmer formul�ret igen
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
							<button>RefEdit</button>
							<button>Remove</button>



						</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>

			<button id="addRefereeButton">Add</button>
			<div id="addRefereeForm" class="box popUp" style="display: none;">
				<h2>Add Referee</h2>
				<form id="addingRefereeForm" action="/EJBFootBookWebProject/register" name="refereeFormType" method="post">
					<label for="RefereeId">Referee ID will be auto-generated!</label> <br><br>

					<!-- Field for Name -->
					<label for="refereeName">Name:</label> 
					<div style="display: flex; justify-content: center;">
						<input type="text" class="bordered-input" id="refereeName" name="refereeName" required>
					</div>
					
					<!-- Field for License -->
					<label for="licenseId">License:</label>
					<div style="display: flex; justify-content: center;">
						<select id="licenseId" name="licenseId" required>
							<option value="">Select License</option>
							<% List<RefereeLicense> licenses = (List<RefereeLicense>) request.getAttribute("licenses");
							if (licenses == null) {
								System.out.println("licenses is null");
							} else {
								for (RefereeLicense license : licenses) {
							%>
							<option value="<%=license.getLicenseId()%>"><%=license.getLicenseId()%></option>
							<% } } %>
						</select>
					</div><br>
					
					<input type="hidden" name="formType" value="addReferee">

					<button type="submit">Submit</button>
				</form>
			</div>
			<script>
				//l�gger till eventlistener f�r att visa formul�ret n�r anv�ndaren klickar p� add
				document
						.getElementById("addRefereeButton")
						.addEventListener(
								"click",
								function() {

									//G�r formul�ret synligt
									document.getElementById("addRefereeForm").style.display = "block";
								});
				//l�gger till en eventlistener f�r att skicka formul�ret
				document
						.getElementById("addRefereeForm")
						.addEventListener(
								"submit",
								function(event) {


									//hindrar standardbeteendet f�r formul�ret


									//G�mmer formul�ret igen
									document.getElementById("addRefereeForm").style.display = "none";
								});
			</script>
		</div>
	</div>

	<script src="Darkmode.js"></script>
</body>
</html>