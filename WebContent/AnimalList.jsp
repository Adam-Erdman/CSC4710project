<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Animal List Application</title>
</head>
<body>
    <center>
        <h1>Adopt A Pet</h1>
        <h2>
            <a href="new">Add New People</a>
           		&nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
            	&nbsp;&nbsp;&nbsp;
            <a href="initDB">Initialize DB</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Birthdate</th>
                <th>Adoption Price</th>
                <th>Traits</th>
                
            </tr>
            <c:forEach items="${animalListForm}" var="animals">
                <tr>
                    <td><c:out value="${animals.getName()}" /></td>
                    <td><c:out value="${animals.getSpecies()}" /></td>
                    <td><c:out value="${animals.getBirthdate()}" /></td>
                    <td><c:out value="${animals.getAdoptionPrice()}" /></td>
                    <td><c:out value="${animals.getTraits()}" /></td>
                               
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>