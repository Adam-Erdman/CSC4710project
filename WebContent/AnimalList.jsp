<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Animal List Application</title>
</head>
<body>
    <center>
        <h1>Adopt A Pet</h1>
        <h2>
           <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
           <a href="myAnimals">My animals</a>
            &nbsp;&nbsp;&nbsp;	
           <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Adoption Price</th>
            </tr>           
            <c:forEach begin="0" end="${fn:length(animalListForm) - 1}" var="i">
                <tr>
                    <td><c:out value="${animalListForm[i].getName()}" /></td>
                    <td><c:out value="${animalListForm[i].getSpecies()}" /></td>
                    <td><c:out value="${ownerFullName[i]}" /></td>                              
                    <td>$<c:out value="${animalListForm[i].getAdoptionPrice()}"/></td>
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>