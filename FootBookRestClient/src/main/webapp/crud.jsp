<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="ISO-8859-1">
<title>FootBook RestClient</title>
<!-- Include Bootstrap CSS -->
<link
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Include jQuery -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Include Bootstrap JS -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
<!-- Include FootBook.js -->
<script src="js/FootBook.js"></script>
<style>
/* Custom Emerald Green (Primary) and Gunmetal Grey (Secondary) colors */
:root {
	--primary-color: #50C878; /* Emerald Green */
	--secondary-color: #8C92AC; /* Gunmetal Grey */
}

/* Apply primary color to header, footer, and buttons */
header, footer {
	background-color: var(--primary-color);
	color: white;
	text-align: center;
	padding: 15px 0;
}

.btn-primary {
	background-color: var(--primary-color);
	border-color: var(--primary-color);
}

.btn-primary:hover {
	background-color: #45B069; /* Slightly darker shade for hover */
	border-color: #45B069;
}

/* Set the body background to secondary color */
body {
	background-color: var(--secondary-color);
	margin: 0; /* Remove default margin */
	font-family: Arial, sans-serif; /* Improves font consistency */
}

.navbar {
	background-color: var(--primary-color);
	color: white;
	display: flex;
	justify-content: center; /* Centers the navbar content horizontally */
	padding: 0.5rem;
}

.navbar .nav-link {
	color: white;
	margin: 0 15px;
}

.container-main {
	margin-top: 20px;
	background-color: transparent;
	padding: 15px;
	border-radius: 10px; /* Optional, to soften corners */
}

.match-card, #matchDisplayBox {
	background-color: white;
	margin-bottom: 20px;
	padding: 10px;
	border: 1px solid #dee2e6;
	/* Optional, gives a light border for better distinction */
}

/* Main application link box styling */
.main-application-box {
	display: inline-block; /* Centers the box inline with the header */
	padding: 15px 20px;
	background-color: white;
	/* Ensure visibility by adding a background color */
	border: 2px solid #50C878; /* Light green border color */
	border-radius: 8px;
	text-decoration: none;
	color: #50C878; /* Light green text */
	font-size: 1.2em;
	font-weight: bold;
	margin-top: 10px;
	transition: all 0.3s ease; /* Adds smooth transitions */
}

/* Hover effect to expand the box */
.main-application-box:hover {
	background-color: #50C878; /* Fill with light green */
	color: white; /* Make text white when hovered */
	transform: scale(1.1); /* Expands the box on hover */
	box-shadow: 0px 0px 15px rgba(0, 0, 0, 0.2);
	/* Adds a shadow for emphasis */
}
</style>
</head>
<body>
	<header>
		<h1>FootBook RestClient</h1>
		<p>
			<a href="http://localhost:8080/EJBFootBookWebProject/home"
				target="_blank" class="main-application-box">Go to Main
				Application</a>
		</p>
	</header>

	<nav class="navbar navbar-expand-lg">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link text-white" href="#">Detailed Search</a>
      </li>
    </ul>
  </nav>

	<div class="container container-main">
		<!-- Weather, City, and IP Section -->
		<div class="row">
			<div class="col-md-12 match-card">
				<div class="card">
					<div class="card-header"
						style="background-color: var(--primary-color); color: white;">
						Location and Weather Information</div>
					<div class="card-body">
						<table class="table table-bordered">
							<tr>
								<th>City: <span id="city"></span></th>
								<th>IP: <span id="ipNbr"></span></th>
							</tr>
							<tr>
								<td>Sunrise: <span id="sunrise"></span></td>
								<td>Sunset: <span id="sunset"></span></td>
							</tr>
							<tr>
								<td>Weather: <span id="weather"></span></td>
								<td>Temperature: <span id="degree"></span></td>
							</tr>
						</table>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-6 match-card">
				<div class="card">
					<div class="card-header"
						style="background-color: var(--primary-color); color: white;">
						Create New Match</div>
					<div class="card-body">
						<form>
							<fieldset id="PersonalFS">
								<legend>Create New Match:</legend>
								<div class="form-group">
									<label for="id">Match ID</label> <input type="text"
										class="form-control" name="matchId" id="id" value="">
								</div>
								<div class="form-group">
									<label for="pitchIdSelect">Pitch ID</label> <select
										class="form-control" id="pitchIdSelect">
										<option value="">Select Pitch ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="refereeIdSelect">Referee ID</label> <select
										class="form-control" id="refereeIdSelect">
										<option value="">Select Referee ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="date">Date</label> <input type="date"
										class="form-control" name="date" id="date" value="">
								</div>
								<div class="form-group">
									<label for="time">Time</label> <input type="time"
										class="form-control" name="time" id="time" value="">
								</div>
								<button type="button" class="btn btn-primary" name="submitBtn"
									value="Add" id="AddBtn">Add</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>

			<div class="col-md-6 match-card">
				<div class="card">
					<div class="card-header"
						style="background-color: var(--primary-color); color: white;">
						Find, Update, and Delete</div>
					<div class="card-body">
						<form>
							<fieldset id="FindUpdateFS">
								<legend>Find, Update, and Delete:</legend>
								<div class="form-group">
									<label for="findMatchIdSelect">Match ID</label> <select
										class="form-control" id="findMatchIdSelect">
										<option value="">Select Match ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="findPitchIdSelect">Pitch ID</label> <select
										class="form-control" id="findPitchIdSelect">
										<option value="">Select Pitch ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="findRefereeIdSelect">Referee ID</label> <select
										class="form-control" id="findRefereeIdSelect">
										<option value="">Select Referee ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="findDate">Date</label> <input type="date"
										class="form-control" name="findDate" id="findDate" value="">
								</div>
								<div class="form-group">
									<label for="findTime">Time</label> <input type="time"
										class="form-control" name="findTime" id="findTime" value="">
								</div>
								<button type="button" class="btn btn-primary" name="submitBtn"
									value="Find" id="FindBtn">Find</button>
								<button type="button" class="btn btn-danger" name="submitBtn"
									value="Delete" id="DeleteBtn">Delete</button>
								<button type="button" class="btn btn-warning" name="submitBtn"
									value="Update" id="UpdateBtn">Update</button>
							</fieldset>
						</form>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12 match-card">
				<div class="card">
					<div class="card-header"
						style="background-color: var(--primary-color); color: white;">
						Match Details</div>
					<div class="card-body" id="matchDisplayBox">
						<p id="displayMatchId">Match ID:</p>
						<p id="displayPitchId">Pitch ID:</p>
						<p id="displayRefereeId">Referee ID:</p>
						<p id="displayMatchDate">Date:</p>
						<p id="displayMatchTime">Time:</p>
					</div>
				</div>
			</div>
		</div>
	</div>

	<footer>
		<p>&copy; CSharpShooters</p>
	</footer>
</body>
</html>
