<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 11.04.2018
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <br>
        <form action="<%=request.getContextPath()%>/edit"  method="post">
            <table>
                <tr>
                    <td>Name: <input type="text" value="<%=request.getParameter("name")%>"name="name"></td>
                    <td>Login: <input type="text" value="<%=request.getParameter("login")%>"name="login"></td>
                    <td>E-mail: <input type="text" value="<%=request.getParameter("email")%>" name="email"></td>
                    <td><input type="submit" value="edit"></td>
                </tr>
            </table>
        <br>
            <form action="<%=request.getContextPath()%>/list" method="get">
                <input type="submit" value="back">
            </form>
        </form>
</body>
</html>
