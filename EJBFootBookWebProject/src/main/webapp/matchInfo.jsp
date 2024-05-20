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

	String message = (String) request.getAttribute("errorMessage");

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
				Referee:
				<%=match.getReferee().getRefereeName()%>
			</p>
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

			<p>GameType: 5 A-Side</p>
			<p>Capacity: 10 Players </p>

		</section>
	</div>
	<div class="box reduced-padding team-members" style="gap: 10px">
		<div>
			<button class="bigger-button" id="updateMatchButton">Update
				Match</button>
		</div>
		<div>
			<form id="deleteMatchForm"
				action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
				method="post">
				<input type="hidden" name="formType" value="deleteMatch">
				<button class="bigger-button" style="background-color: red;"
					type="submit"
					onclick="return confirm('Are you sure you want to remove this match? This action cannot be undone.')">Delete
					Match</button>
			</form>
		</div>
	</div>
	<section class="box colored">
		<section class="box">
			<h1 class="box colored reduced-padding">Users Playing</h1>
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
					<div class="button colored extra-padding highlightable"
						onclick="highlightButton(this); showRemoveButton('<%=user.getUserId()%>')">
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
			<h1 class="box colored reduced-padding">Available Users</h1>
			<%
			Set<User> availableUsers = (Set<User>) request.getAttribute("availableUsers");
			String selectedUser = null;
			if (availableUsers.isEmpty()) {
			%>
			<p>No users available to add to this match</p>
			<%
			}
			%>
			<div class="grid-container">
				<%
				for (User user : availableUsers) {
				%>

				<form
					action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
					method="post">
					<input type="hidden" name="userId" value="<%=user.getUserId()%>">
					<div class="button colored extra-padding highlightable"
						onclick="highlightButton(this); showAddButton('<%=user.getUserId()%>')">
						<h3><%=user.getName()%></h3>
						<p><%=user.getAge()%></p>
						<p><%=user.getGender()%></p>
						<p><%=user.getEmail()%></p>
					</div>
				</form>
				<%
				}
				%>
				<script>
					var numUsers = <%=users.size()%>;
					function showAddButton(userId) {
						document.getElementById("removeUserFromMatchForm").style.display = "none";
						if (numUsers >= 10) {
							return;
						}
						document.getElementById("selectedUserId").value = userId;
						document.getElementById("userIdDisplay").textContent = userId;
						document.getElementById("addUserToMatchForm").style.display = "block";
					}

					function showRemoveButton(userId) {
						document.getElementById("addUserToMatchForm").style.display = "none";
						if (numUsers <= 1) {
							return;
						}
						document.getElementById("selectedRemoveUserId").value = userId;
						document.getElementById("removeUserDisplay").textContent = userId;
						document.getElementById("removeUserFromMatchForm").style.display = "block";
					}

					function highlightButton(clickedButton) {
						// Remove highlight from all buttons
						var buttons = document
								.getElementsByClassName('highlightable');
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
		<input type="hidden" name="formType" value="addUserToMatch"> <input
			type="hidden" name="userId" id="selectedUserId"> <input
			type="hidden" name="matchId" value="<%=match.getMatchId()%>">
		<button type="submit">
			Add User <span id="userIdDisplay"></span> To Match
		</button>
	</form>
	<form id="removeUserFromMatchForm"
		action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
		method="post" style="display: none;">
		<input type="hidden" name="formType" value="removeUserFromMatch">
		<input type="hidden" name="removeUserId" id="selectedRemoveUserId">
		<input type="hidden" name="matchId" value="<%=match.getMatchId()%>">
		<button type="submit">
			Remove User <span id="removeUserDisplay"></span> From Match
		</button>
	</form>

	<div id="lastUserMessage" class="overlay"
		style="display: ${displayStyle};">
		<div class="modalContainer">
			<div class="box colored">
				<p id="errorText">${errorMessage}</p>
				<button onclick="closePopUp()">Close</button>
			</div>
		</div>
	</div>

	<body data-referee-id="<%=match.getReferee().getRefereeId()%>"></body>
	<script>
		function showPopup() {
			document.getElementById("errorMessage").style.display = "block";

		}
		function closePopUp() {
			document.getElementById("lastUserMessage").style.display = "none";
		}
	</script>

	<div id="updateMatchForm" class="overlay" style="display: none;">
		<div class="modalContainer">
			<div class="box colored" id="overlay-box">
				<h2>Update Match</h2>
				<form id="updatingMatchForm"
					action="/EJBFootBookWebProject/matchInfo?matchId=<%=match.getMatchId()%>"
					method="post" onsubmit="return validateForm();">
					<input type="hidden" name="formType" value="updateMatch">

					<!-- Date and Time Picker -->
					<label>Date & Time:</label><br> <input class="bordered-input"
							type="date" name="date" required> <input
							class="bordered-input" type="time" name="time" step="3600"
							required><br>

					<button type="submit">Update Match</button>
					<button type="button" onclick="hideModal()">Close</button>
				</form>
			</div>
		</div>
	</div>

	<script>
		var matchesOnPitch = [
			<% 
				for (Match matchOnPitch : match.getPitch().getMatches()) {
					// Output the match as a JavaScript object
					// This assumes that Match has getId(), getDate(), and getTime() methods
					out.print("{ id: '" + matchOnPitch.getMatchId() + "', date: '" + matchOnPitch.getDate() + "', time: '" + matchOnPitch.getTime() + "' },");
				}
			%>
			];
		</script>
		
		<script>
		var allMatches = [
			<%Set<Match> allMatches = (Set<Match>) request.getAttribute("allMatches");
				for (Match matchInSet : allMatches) {
					// Output the match as a JavaScript object
					// This assumes that Match has getId(), getDate(), and getTime() methods
					out.print("{ id: '" + matchInSet.getMatchId() + "', date: '" + matchInSet.getDate() + "', time: '"
							+ matchInSet.getTime() + "', refereeId: '" + matchInSet.getReferee().getRefereeId() + "' },");
				}%>
				];
		</script>

	<script>
		document
				.getElementById("updateMatchButton")
				.addEventListener(
						"click",
						function() {
							document.getElementById("updateMatchForm").style.display = "block";
							document.getElementById("overlay-box").classList
									.add("pop-up");
						});

		function hideModal() {
			document.getElementById("overlay-box").classList.remove("pop-up");
			document.getElementById("overlay-box").classList.add("pop-down");
			setTimeout(
					function() {
						document.getElementById("updateMatchForm").style.display = "none";
						document.getElementById("overlay-box").classList
								.remove("pop-down");
					}, 300);
		};

		function validateTimeInput() {
			var timeInput = document.querySelector("input[name='time']");
			var timeValue = timeInput.value;

			if (timeValue) {
				var parts = timeValue.split(':');
				if (parts[1] !== "00") {
					timeInput.value = parts[0] + ":00";
				}
			}
			if (new Date().getTime() > new Date(document
					.querySelector("input[name='date']").value
					+ "T" + timeValue).getTime()) {
				alert("Match time cannot be in the past");
				timeInput.value = "";
				return false; // time is in the past
			}
			var date = document.querySelector("input[name='date']").value;
			var time = timeValue;
			for (var i = 0; i < matchesOnPitch.length; i++) {
			    if (matchesOnPitch[i].date === date && matchesOnPitch[i].time === time) {
			        alert("Match already exists at this time");
			        timeInput.value = "";
			        return false; // match already exists at this time
			    }
			}
			return true; // time is not in the past
		}
		
		//Function that returns true if the referee has no other matches at the same time, going through all matches on all pitches
		
		function validateReferee() {
			var refereeId = document.body.getAttribute('data-referee-id');
			var date = document.querySelector("input[name='date']").value;
			var time = document.querySelector("input[name='time']").value;
			for (var i = 0; i < allMatches.length; i++) {
				if (allMatches[i].date === date && allMatches[i].time === time) {
					if (allMatches[i].refereeId === refereeId) {
						alert("Referee already has a match at this time");
						return false; // referee already has a match at this time
					}
				}
			}
   			return true
		}

		function validateForm() {
			if (!validateTimeInput() || !validateReferee()) {
				return false; // prevent form submission
			}
			return true; // allow form submission
		}
	</script>
	<script src="Darkmode.js">
		
	</script>
</body>
</html>