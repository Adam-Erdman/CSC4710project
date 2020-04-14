<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>Search Options</title>
</head>
<body>
    <div align="center">
        <h1>Search Options</h1>
       	<h2>	
        	<a href="welcome">Home</a>
        	 &nbsp;&nbsp;&nbsp;
        	<a href="logout">Logout</a>
	    </h2>

	     <table border="1" cellpadding="5">
	        <tr><th><h2>Select a Search Option</h2></th><tr>
	    	<tr><th><a href="mostExpensiveAnimalsForm">Most Expensive by Trait</a></th></tr>
	    	<tr><th><a href="topAnimals">List Highest Rated Animals</a></th></tr>
	    	<tr><th><a href="topReviewers">List Top Reviews</a></th></tr>
	    	<tr><th><a href="listUserBySpecies">Find Breeder listing Specific Species</a></th></tr>
	    	<tr><th><a href="notRidicAdorbs">Breeders without Ridic Adorbs Reviews</a></th></tr>
	    	<tr><th><a href="notCrayCray">Breeders without Cray Cray Reviews</a></th></tr>
	    	<tr><th><a href="negativeReviewers">List Negative Reviewers</a></th></tr>
	    	<tr><th><a href="listCommonPets">List Pets Someone Else Also Likes</a><br></th></tr>
	    	<tr><th><a href="negativeReviewers">List Negative Reviewers</a></th></tr>
	    	<tr><th><a href="noCrayCrayUsers">Users With No Cray Cray Animals</a></th></tr>
    	</table>
    </div>
</body>
</html>