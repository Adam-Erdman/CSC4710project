<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Never Cray-Cray</title>
</head>
<body>
    <center>
        <h1>Never Cray-Cray</h1>
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
	            <caption><h2>Breeders who NEVER Breed Cray-Cray</h2></caption>
	            <tr>
	            <th>User ID</th>
                <th>User Name</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Email</th>
	                
	            </tr>           
	          <c:forEach items="${neverCray}" var="people">
	              <tr>
	                <td><c:out value="${people.getId()}" /></td>
                    <td><c:out value="${people.getUserName()}" /></td>
                    <td><c:out value="${people.getFirstName()}" /></td>
                    <td><c:out value="${people.getLastName()}" /></td>
                    <td><c:out value="${people.getEmailAddress()}" /></td>
	              </tr>
	          </c:forEach>
	     </table>
    </div>   
</body>
</html>