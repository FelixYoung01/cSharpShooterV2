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
    <%@ include file="header.jsp" %>

    <section class="box" style="width: 800px">
        <h2>About Us</h2>
        <p>Welcome to FootBook! We are your go-to platform for booking football matches on your favorite pitches. Whether you're organizing a friendly match or looking to join a league, FootBook has you covered.</p>
        <p>Our mission is to make the process of booking pitches and organizing games as seamless and enjoyable as possible. Get started today and experience the thrill of playing football like never before!</p>
    </section>

    <!-- Display the images with descriptions side by side -->
    <div style="margin-top: 20px; display: flex; justify-content: space-between;">
        <!-- Elias -->
        <div style="flex-basis: 48%; display: flex; flex-direction: column; align-items: center;">
            <img src="Images/Elias.png" alt="Elias" style="width: 100%; max-width: 200px; height: auto; margin-bottom: 10px;">
            <div style="text-align: center;">
                <h3>Elias</h3>
                <p>Elias founded FootBook with a passion for football and technology. He envisioned a platform that brings players together and simplifies the process of organizing matches.</p>
            </div>
        </div>
        
        <!-- Mehmed -->
        <div style="flex-basis: 48%; display: flex; flex-direction: column; align-items: center;">
            <img src="Images/Mehmed2.png" alt="Mehmed" style="width: 100%; max-width: 200px; height: auto; margin-bottom: 10px;">
            <div style="text-align: center;">
                <h3>Mehmed</h3>
                <p>Mehmed is the driving force behind the user experience at FootBook. With a background in design, he ensures that every aspect of the platform is intuitive and enjoyable for users.</p>
            </div>
        </div>
    </div>
    
    <div style="margin-bottom: 50px;"></div>

	<script src="Darkmode.js"></script>
</body>
</html>

