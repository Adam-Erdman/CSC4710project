<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Search by Trait</title>
</head>
<body>
    <center>
        <h1>Search All Animals by Trait</h1>
        <h2>
        	<a href="AnimalList">List all animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="searchOptions.jsp">Search Options</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
    	<form action="${redirectURL}" method="post">
       		<label for="animals">Choose a trait:</label>
				<select name="trait">
				<c:forEach items="${traitNames}" var="trait">
			  		<option value="<c:out value="${trait}" />"><c:out value="${trait}" /></option>
			  	</c:forEach>
				</select>
      		<input type="submit" value="Submit">
		</form>
    </div>   
</body>
</html>