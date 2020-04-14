<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Adoption Crate</title>
</head>
<body>
    <center>
        <h1>Current Adoption Crate</h1>
        <h2>
           <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="AnimalList">All animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>My Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Adoption Price</th>
            </tr>
            <c:forEach items="${savedAnimals}" var="animal">
                <tr>
                    <td><c:out value="${animal.getName()}" /></td>
                    <td><c:out value="${animal.getSpecies()}" /></td>
                    <td>$<c:out value="${animal.getAdoptionPrice()}" /></td>					
                </tr>
            </c:forEach>
		    <tr><td colspan="3" align="center">Total Price : $${totalPrice}</td></tr>  
	    	<tr><td colspan="3" align="center">
	    		<form action="deleteSavedAnimals" method="post"><input type="submit" value="Buy Pets" /></form>
	    	</td></tr>
        </table>
    </div>   
</body>
</html>