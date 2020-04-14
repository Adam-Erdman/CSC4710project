<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>Welcome!</title>
</head>
<body>
    <div align="center">
        <h1>Welcome ${firstName}!</h1>
           <h2>
           <a href="myAnimals">My animals</a>
            &nbsp;&nbsp;&nbsp;	
            <a href="AnimalList">All animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="searchOptions.jsp">Search Options</a>
            &nbsp;&nbsp;&nbsp;
             <a href="AnimalRegistrationForm">Animal Registration</a>
            &nbsp;&nbsp;&nbsp;
            <c:if test = "${userName == 'root'}">
                <a href="adminControls.jsp">Root Controls</a>
            	&nbsp;&nbsp;&nbsp;
            </c:if>
            <a href="logout">Logout</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
        
    </div>
    <div align ="center">
     <table border="1" cellpadding="5">
            <caption><h2>Favorite Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Adoption Price</th>
                
            </tr>           
          <c:forEach items="${savedAnimals}" var="animals">
                <tr>
                    <td><c:out value="${animals.getName()}" /></td>
                    <td><c:out value="${animals.getSpecies()}" /></td>
                    <td><c:out value="${animals.getOwner()}" /></td>      
                    <td><c:out value="${animals.getAdoptionPrice()}" /></td>                   
                    
                   
                </tr>
            </c:forEach>
        </table>
         <table border="1" cellpadding="5">
            <caption><h2>Favorite Breeders</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Owner</th>
                <th>Adoption Price</th>
               
            </tr>           
          <c:forEach items="${savedBreeders}" var="animals">
                <tr>
                    <td><c:out value="${animals.getName()}" /></td>
                    <td><c:out value="${animals.getSpecies()}" /></td>
                    <td><c:out value="${animals.getOwner()}" /></td>      
                    <td><c:out value="${animals.getAdoptionPrice()}" /></td>                   
                    
                   
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>