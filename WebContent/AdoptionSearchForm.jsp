<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Search Form</title>
</head>
<body>
    <center>
        <h1>Search Adoption Registry</h1>
        <h2>
        	<a href="loginForm">Log-in</a>
            &nbsp;&nbsp;&nbsp;
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="listPeople">List All People</a>
        </h2>
    </center>
    <div align="center">
       
   
       
       <label for="animals">Choose a trait:</label>
			    
			
			<select id="animals">
			<c:forEach items="${animalListFormDropDown}" var="animals">
			  <option value="<c:out value="${animals.getTraits()}" />"><c:out value="${animals.getTraits()}" /></option>
			  </c:forEach>
			</select>
      
        
        
    </div>   
</body>
</html>