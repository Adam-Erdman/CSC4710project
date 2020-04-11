<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Log-in</title>
</head>
<body>
    <div align="center">
        <h1>Log-in</h1>
        <h2>
            <a href="initDB">Initialize DB</a>
        </h2>
        <form action="login">
        <table border="1" cellpadding="5">
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45"/>
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="userpassword" size="45"/>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="login" />
                </td>
            </tr>
        </table>
        </form>
        <c:if test="${failedLogin}">
        	<h3>Failed Login! Incorrect username or password.</h3>
        </c:if>
        New user? Click <a href="newUser">here</a> to sign up.
    </div>   
</body>
</html>