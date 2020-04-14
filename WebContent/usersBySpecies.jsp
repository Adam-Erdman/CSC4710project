<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>People Management Application</title>
</head>
<body>
    <center>
        <h1>People Management</h1>
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
            <caption><h2>List of Breeders</h2></caption>
            <tr>
            	<th>Name</th>
                <th>email</th>
				<th>Save Breeder</th>
            </tr>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.getUserName()}" /> <c:out value="${user.getFirstName()}" /></td>
                    <td><c:out value="${user.getEmailAddress()}" /></td>
                    <td>
						<form action="saveBreeder?ownerID=${user.getId()}" method="post">
							<input type="submit" value="Favorite" NAME="username" />
						</form>                 
                    </td>               
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>