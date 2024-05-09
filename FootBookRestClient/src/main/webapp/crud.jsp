<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/FootBook.css">
<!-- CSS stylesheet -->
<title>FootBook RestClient</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
<!-- jQuery -->
<script src="js/FootBook.js"></script>
<!-- JavaScript file -->
<style>
/* Flex container to hold the find/update form and display box side by side */
#FindUpdateSection {
	display: flex;
	justify-content: space-between;
}

#FindUpdateFS, #matchDisplayBox {
	width: 45%;
	box-sizing: border-box;
}

#matchDisplayBox {
	border: 1px solid #000;
	padding: 10px;
}
</style>
</head>
<body>
	<header>
		<p>FootBook RestClient</p>
	</header>
	<section id="row">
		<nav>
			<ul>
				<li class="active"><a>Match</a></li>
				<li><a>Team Settings</a></li>
				<li><a>Match Reports</a></li>
			</ul>
		</nav>
		<aside>
			<table>
				<tr>
					<th>City: <span id="city"></span></th>
					<th>IP: <span id="ipNbr"></span></th>
				</tr>
				<tr>
					<td> <span id="sunrise"></span></td>
					<td> <span id="sunset"></span></td>
				</tr>
				<tr>
					<td>Weather: <span id="weather"></span></td>
					<td>Temperature: <span id="degree"></span>
					</td>
				</tr>
			</table>
		</aside>
		<section id="main">
			<!-- First Section: Create New Match -->
			<section id="content">
				<article>
					<fieldset id="PersonalFS">
						<legend>Create New Match:</legend>
						Match ID:<br> <input type="text" name="matchId" id="id"
							value=""><br> Pitch ID:<br> <select
							id="pitchIdSelect"><option value="">Select
								Pitch ID</option></select><br> Referee ID:<br> <select
							id="refereeIdSelect"><option value="">Select
								Referee ID</option></select><br> Date:<br> <input type="date"
							name="date" id="date" value=""><br> Time:<br> <input
							type="time" name="time" id="time" value=""><br> <br>
						<input type="button" name="submitBtn" value="Add" id="AddBtn">
					</fieldset>
				</article>
			</section>

			<!-- Second Section: Find, Update, and Delete Matches with Display -->
			<section id="content">
				<div id="FindUpdateSection">
					<!-- Find, Update, and Delete -->
					<fieldset id="FindUpdateFS">
						<legend>Find, Update, and Delete:</legend>
						Match ID:<br> <select id="findMatchIdSelect"><option
								value="">Select Match ID</option></select><br> Pitch ID:<br> <select
							id="findPitchIdSelect"><option value="">Select
								Pitch ID</option></select><br> Referee ID:<br> <select
							id="findRefereeIdSelect"><option value="">Select
								Referee ID</option></select><br> Date:<br> <input type="date"
							name="findDate" id="findDate" value=""><br> Time:<br>
						<input type="time" name="findTime" id="findTime" value=""><br>
						<br> <input type="button" name="submitBtn" value="Find"
							id="FindBtn"> <input type="button" name="submitBtn"
							value="Delete" id="DeleteBtn"> <input type="button"
							name="submitBtn" value="Update" id="UpdateBtn">
					</fieldset>

					<!-- Display Box for Match Details -->
					<fieldset id="matchDisplayBox">
						<legend>Match Details:</legend>
						<p id="displayMatchId">Match ID:</p>
						<p id="displayPitchId">Pitch ID:</p>
						<p id="displayRefereeId">Referee ID:</p>
						<p id="displayMatchDate">Date:</p>
						<p id="displayMatchTime">Time:</p>
					</fieldset>
				</div>
			</section>
		</section>
	</section>
	<footer>
		<p>&copy; CSharpShooters</p>
	</footer>
</body>
</html>
