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
        <h1>Search All Animals by Species</h1>
        <h2>
        	<a href="AnimalList">List all animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="searchOptions.jsp">Search Options</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
    	<form action="usersBySpecies" method="post">
       		<label for="species1">Choose First Animal Species:</label>
				<select name="species1">
				<c:forEach items="${speciesName}" var="s1">
					<option value="<c:out value="${s1}" />"><c:out value="${s1}" /></option>
				</c:forEach>
				</select>
				<br>
			<label for="species2">Choose Second Animal Species:</label>
				<select name="species2">
				<c:forEach items="${speciesName}" var="s2">
					<option value="<c:out value="${s2}" />"><c:out value="${s2}" /></option>
				</c:forEach>
				</select>
				<br>
      		<input type="submit" value="Submit">
		</form>
    </div>   
</body>
</html>