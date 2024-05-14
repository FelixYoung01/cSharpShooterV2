
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="ics.ejb.Pitch" import="java.util.Set" import="ics.ejb.Match" import="ics.ejb.Referee" import="ics.ejb.User"
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
	Pitch pitch = (Pitch) request.getAttribute("pitch");
	String pitchId = pitch.getPitchId();
	%>
	<section class="box">
		<h1>Information about pitch</h1>
		<div>
			<h3>
				Pitch Name:
				<%=pitch.getName()%></h3>
			<p>
				Pitch ID:
				<%=pitchId%></p>
			<img src="Images/<%=pitch.getImageName()%>" alt="Pitch Image" class="bordered-image" style="width: 800px;"/>
		</div>
		<h1>Matches on this pitch</h1>
		<section class="matches">
			<%
			Set<Match> matches = (Set<Match>) request.getAttribute("matchesOnPitch");
			for (Match match : matches) {
			%>
			<div class="match">
				<h3><%=match.getMatchId()%></h3>
				<a
					href="<%=request.getContextPath()%>/matchInfo?matchId=<%=match.getMatchId()%>">
				</a>
				<p>
					Match Date:
					<%=match.getDate()%></p>
				<p>
					Match Time:
					<%=match.getTime()%></p>

			</div>
			<%
			}
			%>
		</section>
        <button id="createMatchButton">Create Match</button>
        <div id="createMatchForm" class="box popUp" style="display: none;">
            <h2>Create Match</h2>
            <form id="creatingMatchForm" action="/EJBFootBookWebProject/pitchInfo" method="post">
            	<input type="hidden" name="formType" value="createMatch">
                <input type="hidden" id="pitchId" name="pitchId" value="<%= pitchId %>">

                <!-- Dropdown for RefereeId -->
                <label for="refereeId">Referee:</label>
                <select id="refereeId" name="refereeId" required>
                    <option value="">Select Referee</option>
                    <%
                    Set<Referee> referees = (Set<Referee>) request.getAttribute("referees");
                    if (referees != null) {
                        for (Referee referee : referees) {
                    %>
                    <option value="<%= referee.getRefereeId() %>"><%= referee.getRefereeName() %></option>
                    <%
                        }
                    }
                    %>
                </select>

                <!-- Dropdown for UserId -->
                <label for="userId">User:</label>
                <select id="userId" name="userId" required>
                    <option value="">Select User</option>
                    <%
                    Set<User> users = (Set<User>) request.getAttribute("users");
                    if (users != null) {
                        for (User user : users) {
                    %>
                    <option value="<%= user.getUserId() %>"><%= user.getName() %></option>
                    <%
                        }
                    }
                    %>
                </select>

                <!-- Date and Time Picker -->
                <label for="date">Date:</label>
                <input type="date" id="date" name="date" required>

                <label for="time">Time:</label>
                <input type="time" id="time" name="time" step="3600" required onchange= "validateTimeInput()">

                <button type="submit">Create Match</button>
            </form>
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
</script>
    <script>
        document.getElementById("createMatchButton").addEventListener("click", function() {
            document.getElementById("createMatchForm").style.display = "block";
        });

        document.getElementById("creatingMatchForm").addEventListener("submit", function(event) {
            document.getElementById("createMatchForm").style.display = "none";
        });
    </script>
	<script src="Darkmode.js"></script>
</body>
</html> 