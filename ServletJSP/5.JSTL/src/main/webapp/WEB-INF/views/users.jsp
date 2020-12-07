<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 10.04.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="1" cellspacing="1" style= "width:500px">

    <tbody>
        <tr>
            <td>User name</td>
            <td>Login</td>
            <td>email</td>
            <td>create date</td>
            <td></td>
            <td></td>
        </tr>
        <c:forEach items="${users}" var="user">
        <tr>
            <td><c:out value="${user.name}"></c:out></td>
            <td><c:out value="${user.login}"></c:out></td>
            <td><c:out value="${user.email}"></c:out></td>
            <td><c:out value="${user.createDate}"></c:out></td>
            <td><form action="${pageContext.servletContext.contextPath}/create" method="get">
                <input type="submit" value="Add new user"> </form></td>
                <td><form action="${pageContext.servletContext.contextPath}/edit" method="get">
                    <input type="submit" value="edit">
                    <input type="hidden" name="name" value= "<c:out value="${user.name}"></c:out>">
                    <input type="hidden" name="login" value="<c:out value="${user.login}"></c:out>">
                    <input type="hidden" name="email" value="<c:out value="${user.email}"></c:out>"> </form>
                </td>
            </tr>
        </c:forEach>
        <tr>
    </tbody>
</table>

</body>
</html>
