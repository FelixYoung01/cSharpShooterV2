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
<style>
    /* Styles for the modal */
    .modal {
        display: none;
        position: fixed;
        z-index: 1000;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgba(0,0,0,0.4);
        padding-top: 60px;
        pointer-events: auto;
    }

    .modal-content {
        background-color: #fefefe;
        color: #000; /* Set text color to black */
        margin: 5% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 600px;
        border-radius: 10px;
        box-shadow: 0 5px 15px rgba(0,0,0,0.3);
        animation-name: animatetop;
        animation-duration: 0.4s;
        z-index: 1001;
        position: relative;
    }

    @keyframes animatetop {
        from {top:-300px; opacity:0}
        to {top:0; opacity:1}
    }

    .close {
        color: red; /* Set the close button color to red */
        position: absolute;
        top: 10px;
        right: 20px;
        font-size: 28px;
        font-weight: bold;
        cursor: pointer;
    }

    .close:hover,
    .close:focus {
        color: darkred; /* Darken the color on hover/focus */
        text-decoration: none;
        cursor: pointer;
    }

    /* Additional styles for modal content */
    .modal-content h2 {
        margin-top: 0;
    }

    .modal-content ul {
        list-style-type: none;
        padding: 0;
    }

    .modal-content ul li {
        padding: 10px 0;
        border-bottom: 1px solid #ccc;
    }

    .modal-content ul li:last-child {
        border-bottom: none;
    }

    /* Disable pointer events for the background content when modal is open */
    body.modal-open {
        pointer-events: none;
    }

    body.modal-open .modal {
        pointer-events: auto;
    }
</style>
</head>
<body>
    <!-- Include the common header, which contains the navigation bar -->
    <jsp:include page="header.jsp" />

    <!-- Main content -->
    <h2 style="font-size: 60px">Welcome to FootBook!</h2>
    <p>Book your football match on your favorite pitches!</p>

    <section class="pitches">
        <h2 class="box colored reduced-padding">Book from one of these available pitches!</h2>

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
    <div id="modal" class="modal">
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
                        
                        $("#modal-content").html(data + '<span id="modal-close" class="close">&times;</span>');
                        $("#modal").show();
                        $("body").addClass("modal-open");
                    }
                });
            });

            // Close the modal when the close button is clicked
            $(document).on("click", "#modal-close", function() {
                $("#modal").hide();
                $("body").removeClass("modal-open");
            });

            // Close the modal when clicking outside of it
            $(window).click(function(event) {
                if ($(event.target).is("#modal")) {
                    $("#modal").hide();
                    $("body").removeClass("modal-open");
                }
            });
        });
    </script>
</body>
</html>
