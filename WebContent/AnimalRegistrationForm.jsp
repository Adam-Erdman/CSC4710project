<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Animal Registration</title>
</head>
<body>
    <center>
        <h1>Add Animal to Adoption Registry</h1>
        <h2>
        	<a href="loginForm">Log-in</a>
            &nbsp;&nbsp;&nbsp;
            <a href="new">Add New People</a>
            &nbsp;&nbsp;&nbsp;
            <a href="list">List All People</a>
        </h2>
    </center>
    <div align="center">
        <c:if test="${animals != null}">
            <form action="updateAnimal" method="post">
        </c:if>
        <c:if test="${animals == null}">
            <form action="insertAnimal" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${animals != null}">
                        Edit Animals
                    </c:if>
                    <c:if test="${animals == null}">
                        Add New Animals
                    </c:if>
                </h2>
            </caption>
                <c:if test="${animals != null}">
                    <input type="hidden" name="id" value="<c:out value='${animals.getId()}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${animals.getName()}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Species: </th>
                <td>
                    <input type="text" name="species" size="45"
                            value="<c:out value='${animals.getSpecies()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Birthdate: </th>
                <td>
                    <input type="text" name="birthdate" size="45"
                            value="<c:out value='${animals.getBirthdate()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Adoption Price: </th>
                <td>
                    <input type="text" name="adoptionPrice" size="45"
                            value="<c:out value='${animals.getAdoptionPrice()}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Traits: </th>
                <td>
                    <input type="text" name="traits" size="45"
                            value="<c:out value='${animals.getTraits()}' />"
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