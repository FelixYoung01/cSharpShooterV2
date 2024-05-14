<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="ics.ejb.Pitch, java.util.Set, java.util.ArrayList"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>FootBook - Football Booking</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
    <!-- Include the common header, which contains the navigation bar -->
    <jsp:include page="header.jsp" />

    <!-- Main content -->
    <h2 style="font-size: 60px">Welcome to FootBook!</h2>
    <p>Book your football match on your favorite pitches!</p>

    <section class="pitches">
        <h2 class="box rounded">Book from one of these available pitches!</h2>


        <%
        Set<Pitch> pitches = (Set<Pitch>) request.getAttribute("pitches");
        if (pitches != null && !pitches.isEmpty()) {
            for (Pitch pitch : pitches) {
                String pitchId = pitch.getPitchId();
                String imagePath = "Images/" + pitch.getImageName();
        %>

        <a class="button"
            href="<%=request.getContextPath()%>/pitchInfo?pitchId=<%=pitchId%>">
            <div id="pitch-label"><%=pitch.getName()%></div> 
            <img src="<%=imagePath%>" class="pitch-image" alt="Pitch Image" />
        </a>

	<h2 style="font-size: 60px">Welcome to FootBook!</h2>
	<p>Book your football match on your favorite pitch!</p>

	<section class="pitches">

		<h2>Choose your pitch of choice to book a match!</h2>



        <%
        }
        } else {
        %>
        <h3>No pitches available</h3>
        <%
        }
        %>

        <%
        String displayTextParam = request.getParameter("displayText");
        if (displayTextParam != null && !displayTextParam.isEmpty()) {
        %>
        <p>COYG</p>
        <%
        }
        %>
    </section>

    <!-- Statistics section -->
    <section class="box colored">
        <div style="display: flex; flex-direction: row;">
            <div class="box count-box" data-type="matches">
                <h1 class="count">${matchCount}</h1>
                <p>Matches Registered</p>
            </div>
            <div class="box count-box" data-type="users">
                <h1 class="count">${userCount}</h1>
                <p>Users Registered</p>
            </div>
            <div class="box count-box" data-type="usersInMatches">
                <h1 class="count">${userOnMatchesCount}</h1>
                <p>Users Registered in Matches</p>
            </div>
            <div class="box count-box" data-type="sessions">
                <h1 class="count">${sessionCount}</h1>
                <p>Session Count</p>
            </div>
        </div>
    </section>

    <!-- Modal for displaying AJAX data -->
    <div id="modal" class="modal" style="display:none;">
        <div class="modal-content" id="modal-content">
            <span id="modal-close" class="close">&times;</span>
        </div>
    </div>

    <!-- Scripts -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="Darkmode.js"></script>
    <script src="weatherInfo.js"></script>
    <script>
        $(document).ready(function() {
            $(".count-box").click(function() {
                var countType = $(this).data("type");
                $.ajax({
                    url: "<%=request.getContextPath()%>/home",
                    method: "GET",
                    data: { type: countType },
                    headers: { "X-Requested-With": "XMLHttpRequest" },
                    success: function(data) {
                        $("#modal-content").html(data);
                        $("#modal").show();
                    }
                });
            });

            $("#modal-close").click(function() {
                $("#modal").hide();
            });

            // Close the modal when clicking outside of it
            $(window).click(function(event) {
                if ($(event.target).is("#modal")) {
                    $("#modal").hide();
                }
            });
        });
    </script>
</body>
</html>
