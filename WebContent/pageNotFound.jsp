<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
    <title>Page not found!</title>
</head>
<body>
    <center>
        <h1>404 page not found</h1>
           <h2>	
                The page <c:out value="${urlInfo}"/> was not found
     		</h2>
    </center>
</body>
</html>