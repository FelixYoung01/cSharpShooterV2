<%@page import="java.util.Map" import="ics.ejb.Pitch"
	import="java.util.Set" import="ics.ejb.Match"
	import="java.time.format.DateTimeFormatter"

	import="java.time.LocalDateTime"
	import="ics.ejb.Referee" import="ics.ejb.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>


	
	

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Pitch Information</title>
<link rel="stylesheet" href="styles.css">

    <script>
    // JavaScript function to ensure the minute part is always set to "00"
    function validateTimeInput() {
        var timeInput = document.getElementById("time");
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
    
 // JavaScript function to show alert if error message is present
    function showErrorAlert(message) {
        alert(message);
    }
</script>
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
	
	String errorMessage = (String) request.getAttribute("errorMessage");
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
	</section>
	<section class="box colored">
		<h1 class="box reduced-padding">Matches</h1>
		<%
		Set<Match> matches = (Set<Match>) request.getAttribute("matchesOnPitch");
		if (matches.isEmpty()) {
		%>
		<p>No matches on this pitch, please create a match</p>
		<%
		} else {
		%>
		<section class="grid-container">
			<%
			Map<String, Integer> matchUserCounts = (Map<String, Integer>) request.getAttribute("matchUserCount");
			for (Match match : matches) {
			%>
			<a class="button extra-padding"
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

        <button id="createMatchButton">Book now</button>
        <div id="createMatchForm" class="box popUp" style="display: none;">
            <h2>Insert Credentials</h2>
            <form id="creatingMatchForm" action="/EJBFootBookWebProject/pitchInfo" method="post">
            	<input type="hidden" name="formType" value="createMatch">
                <input type="hidden" id="pitchId" name="pitchId" value="<%= pitchId %>">


		<div id="createMatchForm" class="overlay" style="display: none;">
			<div class="modalContainer">
				<div class="box colored" id="overlay-box">
					<h2>Create Match</h2>
					<form id="creatingMatchForm"
						action="/EJBFootBookWebProject/pitchInfo" method="post">
						<input type="hidden" name="formType" value="createMatch">
						<input type="hidden" id="pitchId" name="pitchId"
							value="<%=pitchId%>">

						<!-- Dropdown for RefereeId -->
						<label for="refereeId">Referee:</label><br> <select
							class="bordered-input" name="refereeId" required>
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
						</select><br>

						<!-- Dropdown for UserId -->
						<label for="userId">User:</label><br> <select
							class="bordered-input" name="userId" required>
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
						</select><br>


                <label for="time">Time:</label>
                <input type="time" id="time" name="time" required onchange= "validateTimeInput()">

                <button type="submit">Confirm Booking</button>
            </form>
        </div>
    </section>

						<!-- Date and Time Picker -->
						<label>Date & Time:</label><br> <input class="bordered-input"
							type="date" name="date" required> <input
							class="bordered-input" type="time" name="time" step="3600"
							required onchange="validateTimeInput()"><br>

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
			document.getElementById("overlay-box").classList.remove("pop-up");
			document.getElementById("overlay-box").classList.add("pop-down");
			setTimeout(
					function() {
						document.getElementById("createMatchForm").style.display = "none";
						document.getElementById("overlay-box").classList
								.remove("pop-down");
					}, 300);
		};
	</script>
	<script>
		document
				.getElementById("createMatchButton")
				.addEventListener(
						"click",
						function() {
							document.getElementById("createMatchForm").style.display = "block";
							document.getElementById("overlay-box").classList
									.add("pop-up");
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
