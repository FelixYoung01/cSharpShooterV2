<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>About - FootBook</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<button id="dark-mode-toggle" class="icon-button">
		<img src="Images/sun.png" alt="Sun Icon" class="sun-icon">
	</button>
	<h1 class="logo">FootBook</h1>
	<header>
		<nav>
			<ul>
				<li><a href="<%=request.getContextPath()%>/home" class="button">Home</a></li>
				<li><a href="<%=request.getContextPath()%>/register"
					class="button">Register</a></li>
				<li><a href="<%=request.getContextPath()%>/about"
					class="button">About</a></li>
				<li><a href="<%=request.getContextPath()%>/needHelp" class="button">Need Help?</a></li>
			</ul>
		</nav>
		<img src="Images/Arsenal_FC.svg.webp" alt="Arsenal Logo"
			class="arsenal">
	</header>

	<section class="box">
		<h2>About Us</h2>
		<p>Welcome to FootBook! We are your go-to platform for booking
			football matches on your favorite pitches. Whether you're organizing
			a friendly match or looking to join a league, FootBook has you
			covered.</p>
		<p>Our mission is to make the process of booking pitches and
			organizing games as seamless and enjoyable as possible. Get started
			today and experience the thrill of playing football like never
			before!</p>
	</section>

	<div style="margin-bottom: 50px;"></div>

	<footer>
		<p>&copy; 2024 FootBook. All rights reserved.</p>
	</footer>
	<script src="Darkmode.js"></script>
</body>
</html>
