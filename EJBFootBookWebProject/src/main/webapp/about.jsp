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
	<%@ include file="header.jsp"%>

	<h1 style="font-size: 60px;">About Us</h1>
	<p>Learn more about FootBook and the team behind it.</p>

	<section class="box colored" style="max-width: 800px">
		<p>Welcome to FootBook! We are your go-to platform for booking
			football matches on your favorite pitches. Whether you're organizing
			a friendly match or looking to join a league, FootBook has you
			covered.</p>
		<p>Our mission is to make the process of booking pitches and
			organizing games as seamless and enjoyable as possible. Get started
			today and experience the thrill of playing football like never
			before!</p>
		<p>With our user-friendly interface and extensive database of
			football pitches, FootBook makes it easy for players to find and book
			the perfect match. Whether you're a casual player or a seasoned pro,
			FootBook has something for everyone.</p>
		<h3 class="box reduced-padding">Join our community today and
			start booking football matches with ease!</h3>
	</section>

	<section class="box colored">
		<h2>Our Team</h2>
		<p>Meet the team behind FootBook:</p>

		<section class="team-members">
			<div class="box team-member">
				<!-- Elias -->
				<img src="Images/Elias.png" alt="Elias" class="profilepic">
				<div class="team-member-info">
					<h3>Elias</h3>
					<p>Elias founded FootBook with a passion for football and
						technology. He envisioned a platform that brings players together
						and simplifies the process of organizing matches.</p>
				</div>
			</div>

			<div class="box team-member">
				<!-- Mehmed -->
				<img src="Images/Mehmed2.png" alt="Mehmed" class="profilepic">
				<div class="team-member-info">
					<h3>Mehmed</h3>
					<p>Mehmed is the driving force behind the user experience at
						FootBook. With a background in design, he ensures that every
						aspect of the platform is intuitive and enjoyable for users.</p>
				</div>
			</div>
			<div class="box team-member">
				<!-- Mert -->
				<img src="Images/junco.png" alt="Junco" class="profilepic">
				<div class="team-member-info">
					<h3>Junco</h3>
					<p>Junco is the technical genius behind FootBook. With
						expertise in software development, he ensures that the platform is
						secure, reliable, and scalable.</p>
				</div>
			</div>

			<div class="box team-member">
				<!-- Mert -->
				<img src="Images/felix.png" alt="Felix" class="profilepic">
				<div class="team-member-info">
					<h3>Felix</h3>
					<p>Felix is the marketing guru at FootBook. With a passion for
						digital marketing, he ensures that the platform reaches a wide
						audience and continues to grow at a steady pace.</p>
				</div>
			</div>

			<div class="box team-member">
				<!-- Mert -->
				<img src="Images/oskar.png" alt="Oskar" class="profilepic">
				<div class="team-member-info">
					<h3>Oskar</h3>
					<p>Oskar is the operations expert at FootBook. With a
						background in project management, he ensures that the platform
						runs smoothly. He has a passion for football and technology.</p>
				</div>
			</div>
		</section>
	</section>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	<script src="Darkmode.js"></script>
	<script src="weatherInfo.js"></script>

</body>
</html>