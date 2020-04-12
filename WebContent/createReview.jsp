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
        <h1>Create Review for ${reviewAnimal.getName()}</h1>
        <h2>
            <a href="myAnimals">My animals</a>
            &nbsp;&nbsp;&nbsp;
        	<a href="AnimalList">List all animals</a>
            &nbsp;&nbsp;&nbsp;
           <a href="welcome">Home</a>
            &nbsp;&nbsp;&nbsp;
        </h2>
    </center>
    <div align="center">
      <form action="insertReview?animalID=${reviewAnimal.getId()}&ownerID=${ownerID}" method="post">
        <table border="1" cellpadding="5">
          <tr>
      	    <th>Select Rating: </th>
	      <td>
		    <select name="reviewScore">
			   <option value="4">Totes Adorbs</option>
			   <option value="3">Adorbs</option>
			   <option value="2">Cray</option>
			   <option value="1">Cray-Cray</option>
			 </select>
		  </td>
		  </tr>
		  <tr>
             <th colspan="2" align="center">
               Enter your review:
             </th>
           </tr> 
		    <tr>
             <td colspan="2" align="center">
               <INPUT TYPE="text" NAME="review">
             </td>
           </tr>   
		   <tr>
             <td colspan="2" align="center">
               <input type="submit" value="Submit" />
             </td>
           </tr>
	   </table>
	  </form>
    </div>   
</body>
</html>