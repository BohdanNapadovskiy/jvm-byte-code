<%@ page import="ru.napadovskiu.store.UserStore" %>
<%@ page import="ru.napadovskiu.users.User" %>
<%@ page import="java.util.concurrent.CopyOnWriteArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 10.04.2018
  Time: 21:38
  To change this template use File | Settings | File Templates.
--%>
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
        <% CopyOnWriteArrayList<User> listOfUsers = UserStore.getInstance().selectAllUser(); %>
        <% for (User user : listOfUsers) {%>
        <tr>
            <td> <%=user.getName()%> </td>
            <td> <%=user.getLogin()%></td>
            <td><%=user.getEmail()%></td>
            <td><%=user.getCreateDate()%></td>
            <td><form action="<%=request.getContextPath()%>/create" method="get">
                <input type="submit" value="Add new user"> </form></td>
                <td><form action="<%=request.getContextPath()%>/edit" method="get">
                    <input type="submit" value="edit">
                    <input type="hidden" name="name" value= "<%=user.getName()%>">
                    <input type="hidden" name="login" value="<%=user.getLogin()%>">
                    <input type="hidden" name="email" value="<%=user.getEmail()%>"> </form>
                </td>
            </tr>
            <%} %>
        <tr>
    </tbody>
</table>

</body>
</html>
