<%@ page language="java" contentType="text/html; charset=ISO-8859-1" import="ics.ejb.Pitch"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <%
    Pitch pitch = (Pitch) request.getAttribute("pitch");
    %>
    <h1>Information about pitch</h1>
<div>
    <h3>Pitch Name: <%= pitch.getName() %></h3>
    <p>Pitch ID: <%= pitch.getPitchId() %></p>
</div>
    

</body>
</html>