<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Help - FootBook</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%@ include file="header.jsp"%>

	<!-- Banner section -->
	<div class="banner">
		<h1 style="font-size: 78px; text-align: center;">We're here to
			help.</h1>
		<p style="font-size: 24px;">If you didn't find what you were
			looking for or need some help, please check the FAQ below or contact
			us. We're just one click away.</p>
	</div>

	<!-- Main content section -->
	<section class="box" style="width: 600px">
		<h2>Book a Match</h2>
		<p>Go to the home page and select your pitch of choice. After
			choosing the pitch, you are able to book a match at a specific date
			and time with a referee of your choice.</p>
	</section>

	<section class="box" style="width: 600px">
		<h2>Create a User</h2>
		<p>Go to the register page and click on the add user button. Fill
			in the required information and you are ready to go.</p>
	</section>

	<section class="box" style="width: 600px">
		<h2>Create a Referee</h2>
		<p>Go to the register page and click on the add referee button.
			Fill in the required information and you are ready to go.</p>
	</section>


	<!-- Contact Form -->
	<section class="box" style="width: 600px">
		<h2>Contact Us</h2>
		<form id="contactForm">
			<label for="name">Name:</label> <input type="text" id="name"
				name="name" required><br>
			<br> <label for="email">Email:</label> <input type="email"
				id="email" name="email" required><br>
			<br> <label for="message">Message:</label><br>
			<textarea id="message" name="message" rows="4" cols="50" required></textarea>
			<br>
			<br> <input type="button" value="Send"
				onclick="validateAndSubmit()">
		</form>
	</section>

	<script>
		function validateAndSubmit() {
			var name = document.getElementById("name").value;
			var email = document.getElementById("email").value;
			var message = document.getElementById("message").value;

			// Validate email format using a regular expression
			var emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
			if (!emailPattern.test(email)) {
				alert("Please enter a valid email address.");
				return; // Exit function if email format is invalid
			}

			// If email format is valid, display success message
			alert("Success! Your message was sent.");

			// Optionally, reset the form fields after displaying the message
			document.getElementById("name").value = "";
			document.getElementById("email").value = "";
			document.getElementById("message").value = "";
		}

		function showSuccess() {
			// Show a success message in a popup
			alert("Success! Your message was sent.");

			// Optionally, you can reset the form fields after displaying the message
			document.getElementById("name").value = "";
			document.getElementById("email").value = "";
			document.getElementById("message").value = "";
		}
	</script>
	<div style="margin-bottom: 50px;"></div>

	<script src="Darkmode.js"></script>
</body>
</html>
