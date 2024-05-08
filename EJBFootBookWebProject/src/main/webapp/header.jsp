<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<button id="dark-mode-toggle" class="icon-button">
    <img src="Images/sun.png" alt="Sun Icon" class="sun-icon">
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