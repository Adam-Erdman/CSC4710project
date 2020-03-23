<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>People Application</title>
</head>
<body>
    <center>
        <h1>People Management</h1>
        <h2>
        	<a href="loginForm">Log-in</a>
            &nbsp;&nbsp;&nbsp;
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="listPeople">List All People</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${people != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${people == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${people != null}">
                        Edit People
                    </c:if>
                    <c:if test="${people == null}">
                        Add New People
                    </c:if>
                </h2>
            </caption>
                <c:if test="${people != null}">
                    <input type="hidden" name="id" value="<c:out value='${people.getId()}' />" />
                </c:if>           
            <tr>
                <th>First Name: </th>
                <td>
                    <input type="text" name="firstname" size="45"
                            value="<c:out value='${people.getFirstName()}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Last Name: </th>
                <td>
                    <input type="text" name="lastname" size="45"
                            value="<c:out value='${people.getLastName()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Email: </th>
                <td>
                    <input type="text" name="emailaddress" size="45"
                            value="<c:out value='${people.getEmailAddress()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Username: </th>
                <td>
                    <input type="text" name="username" size="45"
                            value="<c:out value='${people.getUserName()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Password: </th>
                <td>
                    <input type="password" name="userpassword" size="45"
                            value="<c:out value='${people.getUserPassword()}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>