<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>Create Review</title>
</head>
<body>
    <center>
        <h1>Review for ${animalName} by ${review.getReviewersName()}</h1>
        <h2>
            <a href="myAnimals">Back</a>
            &nbsp;&nbsp;&nbsp;
        	<a href="AnimalList">List all animals</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
          <tr>
      	    <th>Rating: </th>
	      <td>
			<c:choose>
				<c:when test="${review.getReviewScore() == 4}">
			      	Totes Adorbs
			    </c:when>    
			    <c:when test="${review.getReviewScore() == 3}">
			      	Adorbs
			    </c:when>    
			       <c:when test="${review.getReviewScore() == 2}">
			      	Cray
			    </c:when>    
			    <c:otherwise>
					Cray-Cray
			    </c:otherwise>
			</c:choose>
		  </td>
		  </tr>
		  <tr>
             <th colspan="2" align="center">
               Review:
             </th>
           </tr> 
		    <tr>
             <td colspan="2" align="center">
               <c:out value="${review.getReview()}"></c:out>
             </td>
           </tr>   
	   </table>
    </div>   
</body>
</html>