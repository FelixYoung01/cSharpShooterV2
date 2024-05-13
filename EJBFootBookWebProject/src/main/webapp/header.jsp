<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<button id="dark-mode-toggle" class="icon-button">
	<img src="Images/sun.png" alt="Sun Icon" class="sun-icon">
</button>
<h1 class="logo">FootB<span id="logo-o"><img src='Images/footballpattern.png'>o</span>ok</h1>
<div class="box" id="weatherBox">
	<h3>Weather Information</h3>
	<p>
		City: <span id="city"></span>
	</p>
	<p>
		IP: <span id="ipNbr"></span>
	</p>
	<p>
		 <span id="sunrise"></span>
	</p>
	<p>
		 <span id="sunset"></span>
	</p>
	<p>
		Weather: <span id="weather"></span>
	</p>
	<p>
		Temperature: <span id="degree"></span>
	</p>
</div>
<header>
	<nav>
		<ul>
			<li><a href="<%=request.getContextPath()%>/home" class="button">Home</a></li>
			<li><a href="<%=request.getContextPath()%>/register"
				class="button">Register</a></li>
			<li><a href="<%=request.getContextPath()%>/about" class="button">About</a></li>
			<li><a href="<%=request.getContextPath()%>/needHelp"
				class="button">Need Help?</a></li>
		</ul>
	</nav>
</header>
<img src="Images/footballpattern.png" alt="Football"
	class="upperleftball" />
<img src="Images/footballpattern.png" alt="Football"
	class="lowerrightball" />
<footer>&copy; 2024 FootBook. All rights reserved.</footer>