<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>All Animals</title>
</head>
<body>
    <center>
        <h1>Adopt A Pet</h1>
        <h2>
           <a href="myAnimals">My animals</a>
            &nbsp;&nbsp;&nbsp;
           <a href="searchOptions.jsp">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${mt5reviews}">
        	<h2>Cannot post more than five reviews!</h2>
        </c:if>
        <table border="1" cellpadding="5">
            <caption><h2>List of Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Adoption Price</th>
                <th>Review</th>
                <th>Save Animal</th>
                <th>Save Breeder</th>
            </tr>           
            <c:forEach begin="0" end="${fn:length(animalListForm) - 1}" var="i">
                <tr>
                    <td><c:out value="${animalListForm[i].getName()}" /></td>
                    <td><c:out value="${animalListForm[i].getSpecies()}" /></td>
                    <td><c:out value="${ownerFullName[i]}" /></td>                      
                    <td>$<c:out value="${animalListForm[i].getAdoptionPrice()}"/></td>
                    <td><a href="createReview?animalID=<c:out value='${animalListForm[i].getId()}'/>">Create Review</a></td>
                    <td>
                     <form action="saveAnimal?animalID=${animalListForm[i].getId()}&ownerID=${animalListForm[i].getOwner()}" method="post">
                    <input type="submit" value="Favorite" />
                     </form>
                    </td>
                    
                     <td>
                     <form action="saveBreeder?animalID=${animalListForm[i].getId()}&ownerID=${animalListForm[i].getOwner()}" method="post">
                    <input type="submit" value="Favorite" NAME="username" />
                     </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>