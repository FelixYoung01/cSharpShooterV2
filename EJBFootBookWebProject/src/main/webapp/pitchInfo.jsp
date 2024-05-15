<%@page import="java.util.Map" import="ics.ejb.Pitch"
	import="java.util.Set" import="ics.ejb.Match"
	import="java.time.format.DateTimeFormatter"
	import="java.time.LocalDateTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="ics.ejb.Pitch" import="java.util.Set" import="ics.ejb.Match"
	import="ics.ejb.Referee" import="ics.ejb.User"
	pageEncoding="ISO-8859-1"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pitch Information</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<%
	Pitch pitch = (Pitch) request.getAttribute("pitch");
	String pitchId = pitch.getPitchId();

	// Define the date formatter
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	// Format the founded date
	String foundedDateFormatted = (pitch.getFounded() != null) ? pitch.getFounded().format(formatter) : "Not Available";
	%>
	<section class="box colored">
		<h1>Pitch Information</h1>
		<div>
			<h3>
				Pitch Name:
				<%=pitch.getName()%></h3>
			<p>
				Pitch ID:
				<%=pitchId%></p>
			<p>
				Founded:
				<%=foundedDateFormatted%></p>

			<img src="Images/<%=pitch.getImageName()%>" alt="Pitch Image"
				class="bordered-image" style="width: 800px;" />



		</div>

		<h1>Matches on this pitch</h1>
		<section class="grid-container">
			<%
			Set<Match> matches = (Set<Match>) request.getAttribute("matchesOnPitch");
			Map<String, Integer> matchUserCounts = (Map<String, Integer>) request.getAttribute("matchUserCount");
			for (Match match : matches) {
			%>
			<a class="button"
				href="<%=request.getContextPath()%>/matchInfo?matchId=<%=match.getMatchId()%>">
				<h3><%=match.getMatchId()%></h3>
				<p>
					Match Date:
					<%=match.getDate()%>
				</p>
				<p>
					Match Time:
					<%=match.getTime()%>
				</p>
				<p>
					Number of players:
					<%=matchUserCounts.get(match.getMatchId())%>
				</p>
			</a>
			<%
			}
			%>
		</section>
		<button id="createMatchButton">Create Match</button>

		<div id="createMatchForm" class="overlay" style="display: none;">
			<div class="modalContainer">
				<div class="box colored">
					<h2>Create Match</h2>
					<form id="creatingMatchForm"
						action="/EJBFootBookWebProject/pitchInfo" method="post">
						<input type="hidden" name="formType" value="createMatch">
						<input type="hidden" id="pitchId" name="pitchId"
							value="<%=pitchId%>">

						<!-- Dropdown for RefereeId -->
						<label for="refereeId">Referee:</label> <select id="refereeId"
							name="refereeId" required>
							<option value="">Select Referee</option>
							<%
							Set<Referee> referees = (Set<Referee>) request.getAttribute("referees");
							if (referees != null) {
								for (Referee referee : referees) {
							%>
							<option value="<%=referee.getRefereeId()%>"><%=referee.getRefereeName()%></option>
							<%
							}
							}
							%>
						</select>

						<!-- Dropdown for UserId -->
						<label for="userId">User:</label> <select id="userId"
							name="userId" required>
							<option value="">Select User</option>
							<%
							Set<User> users = (Set<User>) request.getAttribute("users");
							if (users != null) {
								for (User user : users) {
							%>
							<option value="<%=user.getUserId()%>"><%=user.getName()%></option>
							<%
							}
							}
							%>
						</select>

						<!-- Date and Time Picker -->
						<label for="date">Date:</label> <input type="date" id="date"
							name="date" required> <label for="time">Time:</label> <input
							type="time" id="time" name="time" step="3600" required
							onchange="validateTimeInput()">

						<button type="submit">Create Match</button>
                        <button type="button" onclick="hideModal()">Close</button>
					</form>
				</div>
			</div>
		</div>
	</section>
	<script>
		// JavaScript function to ensure the minute part is always set to "00"
		function validateTimeInput() {
			var timeInput = document.getElementById("matchTime");
			var timeValue = timeInput.value;

			if (timeValue) {
				var parts = timeValue.split(':');
				if (parts[1] !== "00") {
					timeInput.value = parts[0] + ":00";
				}
			}
		}

		// JavaScript function to validate form submission
		function validateForm(event) {
			validateTimeInput();
			return true;
		}

        function hideModal() {
					document.getElementById('createMatchForm').style.display = 'none';
				}
	</script>
	<script>
		document
				.getElementById("createMatchButton")
				.addEventListener(
						"click",
						function() {
							document.getElementById("createMatchForm").style.display = "block";
						});

		document
				.getElementById("creatingMatchForm")
				.addEventListener(
						"submit",
						function(event) {
							document.getElementById("createMatchForm").style.display = "none";
						});
	</script>
	<script src="Darkmode.js"></script>
</body>
</html>
