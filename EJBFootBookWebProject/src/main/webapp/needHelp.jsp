<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Help - FootBook</title>
    <link rel="stylesheet" href="styles.css">
</head>
<body>
    <div class="background"></div>
    <div class="content">
        <button class="icon-button">
            <img src="Images/weather.webp" alt="Weather Icon" class="weather-icon">
        </button>
        <h1 class="logo">FootBook</h1>
        <header>
            <nav>
                <ul>
                    <li><a href="<%=request.getContextPath()%>/home" class="button">Home</a></li>
                    <li><a href="<%=request.getContextPath()%>/register" class="button">Register</a></li>
                    <li><a href="<%=request.getContextPath()%>/about" class="button">About</a></li>
                    <li><a href="<%=request.getContextPath()%>/needHelp" class="button">Need Help?</a></li>
                </ul>
            </nav>
            <img src="Images/Arsenal_FC.svg.webp" alt="Arsenal Logo" class="arsenal">
        </header>

        <section class="box">
            <h2>Need Help?</h2>
            <p>Contact our support team for assistance.</p>
            <!-- Add contact information or support details here -->
        </section>

        <footer>
            <p>&copy; 2024 FootBook. All rights reserved.</p>
        </footer>
    </div>
</body>
</html>
