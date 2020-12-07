<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 10.04.2018
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Title</title>
</head>
</body>
    <br>
        <form action="${pageContext.servletContext.contextPath}/create" method="post">
            <table>
                <tr>
                    <td>Name: <input type="text" name="name"/></td>
                    <td>Login: <input type="text" name="login"/></td>
                    <td>E-mail: <input type="text" name="email"/></td>
                    <td><input type="submit" value="add"></td>
                </tr>
            </table>
        </form>
    <br><form Name ="back" action="${pageContext.servletContext.contextPath}/" method="get">
        <input type="submit" value="back"></form>
</body>
</html>
