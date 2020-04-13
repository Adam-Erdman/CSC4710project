<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Top Reviewers</title>
</head>
<body>
    <center>
        <h1>Top 5 Reviewers</h1>
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
            <caption><h2>List of People</h2></caption>
            <tr>
            	<th>Reviewer Name</th>
                <th>Number of Reviews</th>
            </tr>
			<c:forEach begin="0" end="${fn:length(reviewers) - 1}" var="i">
				<tr>
					<td><c:out value="${ownerFullName[i]}" /></td> 
					<td><c:out value="${reviewers[i].getReviewCount()}" /></td>    
				</tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>