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
			<li class="nav-item active"><a class="nav-link text-white"
				href="#">User</a></li>
		</ul>
	</nav>

	<div class="container container-main">
		<!-- Weather, City, and IP Section -->
		<div class="row">
			<div class="col-md-12 match-card">
				<div class="card">
					<div class="card-header"
						style="background-color: var(--primary-color); color: white;">Location
						and Weather Information</div>
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
						style="background-color: var(--primary-color); color: white;">Create
						New User</div>
					<div class="card-body">
						<form>
							<fieldset id="PersonalFS">
								<legend>Create New User:</legend>
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
									<label for="userGender">Gender (M/F)</label> <input type="text"
										class="form-control" name="userGender" id="userGender"
										value="">
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
						style="background-color: var(--primary-color); color: white;">Find,
						Update, and Delete User</div>
					<div class="card-body">
						<form>
							<fieldset id="FindUpdateFS">
								<legend>Find, Update, and Delete:</legend>
								<div class="form-group">
									<label for="findUserIdSelect">User ID</label> <select
										class="form-control" id="findUserIdSelect">
										<option value="">Select User ID</option>
									</select>
								</div>
								<div class="form-group">
									<label for="findUserName">Name</label> <input type="text"
										class="form-control" id="findUserName">
								</div>
								<div class="form-group">
									<label for="findUserAge">Age</label> <input type="number"
										class="form-control" id="findUserAge">
								</div>
								<div class="form-group">
									<label for="findUserEmail">Email</label> <input type="email"
										class="form-control" id="findUserEmail">
								</div>
								<div class="form-group">
									<label for="findUserGender">Gender (M/F)</label> <input
										type="text" class="form-control" id="findUserGender">
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
						style="background-color: var(--primary-color); color: white;">User
						Details</div>
					<div class="card-body" id="userDisplayBox">
						<p id="displayUserId">User ID:</p>
						<p id="displayUserName">Name:</p>
						<p id="displayUserAge">Age:</p>
						<p id="displayUserEmail">Email:</p>
						<p id="displayUserGender">Gender:</p>
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
