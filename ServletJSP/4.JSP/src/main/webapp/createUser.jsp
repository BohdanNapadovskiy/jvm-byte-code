<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 10.04.2018
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
</body>
    <br>
        <form action="<%=request.getContextPath()%>/create" method="post">
            <table>
                <tr>
                    <td>Name: <input type="text" name="name"/></td>
                    <td>Login: <input type="text" name="login"/></td>
                    <td>E-mail: <input type="text" name="email"/></td>
                    <td><input type="submit" value="add"></td>
    <br><form Name ="back" action="<%=request.getContextPath()%>/list" method="get">
        <input type="submit" value="back"></form>
                </tr>
            </table>
    </form>
</body>
</html>
