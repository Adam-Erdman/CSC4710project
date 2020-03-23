<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <center>
        <h1>Successful Log-in ${userName}!</h1>
           <h2>
            <a href="AnimalList">List an animal</a>
            &nbsp;&nbsp;&nbsp;
            <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="listPeople">Logout</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </center>
    
</body>
</html>