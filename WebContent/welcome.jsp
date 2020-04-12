<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <div align="center">
        <h1>Welcome ${userName}!</h1>
           <h2>
           <a href="myAnimals">My animals</a>
            &nbsp;&nbsp;&nbsp;	
            <a href="AnimalList">All animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
             <a href="AnimalRegistrationForm">Animal Registration</a>
            &nbsp;&nbsp;&nbsp;
            <c:if test = "${userName == 'root'}">
                <a href="adminControls.jsp">Root Controls</a>
            	&nbsp;&nbsp;&nbsp;
            </c:if>
            <a href="logout">Logout</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </div>
</body>
</html>