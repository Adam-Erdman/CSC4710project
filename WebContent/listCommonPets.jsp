<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Search by Species</title>
</head>
<body>
    <center>
        <h1>List Common Pets</h1>
        <h2>
        	<a href="AnimalList">List all animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="searchOptions.jsp">Search Options</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
    	<table border="1" cellpadding="5">
	            <caption><h2>Shared Favorite Pets</h2></caption>
	            <tr>
	                <th>Pet Name</th>
	                <th>Species</th>
	                <th>Owner</th>
	                <th>Adoption Price</th>
	                
	            </tr>           
	          <c:forEach items="${commonPets}" var="animal">
	              <tr>
	                  <td><c:out value="${animal.getName()}" /></td>
	                  <td><c:out value="${animal.getSpecies()}" /></td>
	                  <td><c:out value="${animal.getOwner()}" /></td>      
	                  <td><c:out value="${animal.getAdoptionPrice()}" /></td>       
	                                     
	              </tr>
	          </c:forEach>
	     </table>
    </div>   
</body>
</html>