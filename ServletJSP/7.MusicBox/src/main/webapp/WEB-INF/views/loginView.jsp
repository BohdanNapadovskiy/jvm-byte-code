<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 24.04.2018
  Time: 21:41
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title></title>
</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<style> <%@include file="/css/style.css"%> </style>
<body>
<c:if test="${error != ''}">
    <div style="background-color: red">
        <c:out value="${error}"/>
    </div>
</c:if>

<form class="form-4" action="${pageContext.servletContext.contextPath}/login" method="post">
    <h1>Форма входа</h1>
    <p>
        <label for="login">Логин или email</label>
        <input type="text" name="login" placeholder="Логин или email" required>
    </p>
    <p>
        <label for="password">Пароль</label>
        <input type="password" name='password' placeholder="Пароль" required>
    </p>
     <p>
         <input type="submit" name="submit" value="Продолжить">
     </p>
</form>

</body>
</html>
