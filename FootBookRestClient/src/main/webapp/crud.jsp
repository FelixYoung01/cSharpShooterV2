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
<!-- Include Custom CSS -->
<link href="css/footbook.css" rel="stylesheet">
<style>
body {
	background-color: #50C878; /* Cadmium Green */
	color: white;
}

header {
	position: relative;
	padding: 10px;
	margin-bottom: 20px; /* Add some space below the header */
}

.header-content {
	display: flex;
	flex-direction: column;
	align-items: center;
	margin: 0 auto;
	max-width: 800px; /* Match the max width of the encapsulated box */
}

.header-title {
	text-align: center;
	margin-bottom: 10px;
}

.main-application-box {
	display: inline-block;
	background-color: #097969; /* Darker green for the button */
	color: white;
	padding: 10px 20px;
	border: none;
	border-radius: 4px;
	text-decoration: none;
	margin-top: 10px;
	transition: background-color 0.3s;
}

.main-application-box:hover {
	background-color: #50C878; /* Even darker green on hover */
}

.weather-info {
	position: absolute;
	top: 10px;
	left: 10px;
	color: black;
	max-width: 300px;
}

.container-main {
	max-width: 1200px; /* Increase max-width to accommodate both sections */
	margin: 0 auto; /* Center the container */
}

.encapsulated-box {
	background-color: #f8f9fa; /* Light grey background */
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	margin: 20px;
}

.left-section, .right-section {
	padding: 20px;
}

legend, .card-header {
	color: black;
	text-align: left;
}

.form-group label {
	color: black;
	text-align: left;
	display: block;
}

#userDisplayBox p {
	color: black;
	text-align: left;
}

.list-group-item {
	background-color: white;
	color: black;
}
</style>
</head>
<body>
	<header>
		<div class="weather-info">
			<div class="card">
				<div class="card-header">Location and Weather Information</div>
				<div class="card-body">
					<table class="table table-bordered">
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
							<td>Temperature: <span id="degree"></span></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
		<div class="header-content">
			<div class="header-title">
				<h1>FootBook RestClient</h1>
				<a href="http://localhost:8080/EJBFootBookWebProject/home"
					target="_blank" class="main-application-box">Go to Main
					Application</a>
			</div>
		</div>
	</header>

	<nav class="navbar navbar-expand-lg">
		<ul class="navbar-nav">
			<li class="nav-item active"><a class="nav-link text-white"
				href="#"></a></li>
		</ul>
	</nav>

	<div class="container container-main">
		<div class="encapsulated-box">
			<div class="row">
				<div class="col-md-6 left-section">
					<div class="card">
						<div class="card-body">
							<form>
								<fieldset id="PersonalFS">
									<legend>Create, Find, Update, and Delete User:</legend>
									<div class="form-group">
										<label for="userId">User ID</label> <input type="text"
											class="form-control" name="userId" id="userId" value="">
									</div>
									<div class="form-group">
										<label for="userName">Name</label> <input type="text"
											class="form-control" name="userName" id="userName" value="">
									</div>
									<div class="form-group">
										<label for="userAge">Age</label> <input type="number"
											class="form-control" name="userAge" id="userAge" value="">
									</div>
									<div class="form-group">
										<label for="userEmail">Email</label> <input type="email"
											class="form-control" name="userEmail" id="userEmail" value="">
									</div>
									<div class="form-group">
										<label for="userGender">Gender (M/F)</label> <input
											type="text" class="form-control" name="userGender"
											id="userGender" value="">
									</div>
									<button type="button" class="btn btn-primary" name="submitBtn"
										value="Add" id="AddBtn">Add</button>
									<button type="button" class="btn btn-warning" name="submitBtn"
										value="Update" id="UpdateBtn">Update</button>
									<button type="button" class="btn btn-danger" name="submitBtn"
										value="Delete" id="DeleteBtn">Delete</button>
									<button type="button" class="btn btn-secondary"
										name="submitBtn" value="Find" id="FindBtn">Find</button>
								</fieldset>
							</form>
						</div>
					</div>

					<div class="card">
						<div class="card-header">User Details</div>
						<div class="card-body" id="userDisplayBox">
							<p id="displayUserId">User ID:</p>
							<p id="displayUserName">Name:</p>
							<p id="displayUserAge">Age:</p>
							<p id="displayUserEmail">Email:</p>
							<p id="displayUserGender">Gender:</p>
						</div>
					</div>
				</div>

				<div class="col-md-6 right-section">
					<div class="card">
						<div class="card-header">All Users</div>
						<div class="card-body">
							<ul id="userList" class="list-group">
								<!-- Users will be dynamically added here -->
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Include jQuery -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<!-- Include Bootstrap JS -->
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
	<!-- Include Custom JS -->
	<script src="js/FootBook.js"></script>
</body>
</html>
