<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
  <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>My Pets</title>
</head>
<body>
    <center>
        <h1>My Current Pets</h1>
        <h2>
           <a href="AnimalListFormDropDown">Search Animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="AnimalList">All animals</a>
            &nbsp;&nbsp;&nbsp;
            <a href="welcome">Home</a>
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>My Pets</h2></caption>
            <tr>
                <th>Pet Name</th>
                <th>Species</th>
                <th>Adoption Price</th>
				 <th>Reviews</th>
            </tr>
            <c:forEach items="${animals}" var="animal">
                <tr>
                    <td><c:out value="${animal.getName()}" /></td>
                    <td><c:out value="${animal.getSpecies()}" /></td>
                    <td>$<c:out value="${animal.getAdoptionPrice()}" /></td>
                    <c:if test="${animal.getReviewSize() > 0}">	
                   		<td>			       
		                  	<c:forEach begin="0" end="${animal.getReviewSize() - 1}" var="i">
				            	<a href="showReview?reviewID=<c:out value='${animal.getReviewID(i)}'/>">
				            		<c:out value="${animal.getReviewersName(i)}"/>
								</a><br> 			 
		            		</c:forEach>
	            		</td>
					</c:if>
					<c:if test="${animal.getReviewSize() == 0}">
						<td>
							None
						</td>
					</c:if>						
                </tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>