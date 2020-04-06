<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>My Pets</title>
</head>
<body>
    <center>
        <h1>My Current Pets</h1>
        <h2>
           <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="AnimalList">All animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="welcome">Home</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Adoption Price</th>
            </tr>
            <c:forEach items="${animals}" var="animals">
                <tr>
                    <td><c:out value="${animals.getName()}" /></td>
                    <td><c:out value="${animals.getSpecies()}" /></td>
                    <td><c:out value="${animals.getOwner()}" /></td>                              
                    <td><c:out value="${animals.getAdoptionPrice()}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>