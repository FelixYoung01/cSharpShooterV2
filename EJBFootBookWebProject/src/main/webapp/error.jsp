<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Error - FootBook</title>
<link rel="stylesheet" href="styles.css">
</head>
<body>
	<div class="box colored">
		<div class="box">
			<h1>Whoops! An error occurred!</h1>
		</div>
		<% String errorMessage = (String) request.getAttribute("errorMessage"); %>
		<% if (errorMessage != null) { %>
		<h3>
			Error:
			<%= errorMessage %></h3>
		<%
		}
		%>
		<a class="button" style="width: 200px" href="<%=request.getContextPath()%>/home">Back to Home</a>
	</div>
</body>
</html>