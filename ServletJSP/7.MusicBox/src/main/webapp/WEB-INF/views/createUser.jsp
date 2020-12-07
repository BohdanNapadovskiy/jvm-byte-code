<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 10.04.2018
  Time: 21:18
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    <script>
        $(document).ready(function (){
            $("#country").empty();
            $.ajax ({
                url: "musicJson",
                type: "get",
                success: function (data) {
                    var musicType = $("#musicType");
                    $.each(data, function (i, value) {
                        musicType.append($("<option></option>").val(value.music_name).html(value.music_name));
                    });
                }
            });
        });
    </script>


    <script> <%@include file="/js/validate.js"%>  </script>
    <style> <%@include file="/css/style.css"%> </style>
    <title>Title</title>
</head>
</body>

<form Name ="back" action="${pageContext.servletContext.contextPath}/" method="get">
    <div class="button-back">
        <input type="submit" value="<-back" name="backButton">
    </div>
</form>

<div>
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        <div class = "create">
            <div class = "row">
                <div class="col-25">
                    <label> Имя: </label>
                </div>
                <div class="col-75">
                    <input type="text" name ="name"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Логин: </label>
                </div>
                <div class="col-75">
                    <input type="text" name="login"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> E-mail: </label>
                </div>
                <div class="col-75">
                    <input type="text" name="email"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Address: </label>
                </div>
                <div class="col-75">
                    <input type="text" name="address"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Роль: </label>
                </div>
                <div class="col-75">
                    <select name="role" required>
                        <option value="ADMIN">ADMIN</option>
                        <option value="USER">USER</option>
                        <option value="MANDATOR">MANDATOR</option>
                    </select>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Пароль:</label>
                </div>
                <div class="col-75">
                    <input type="password" name="password"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Пароль повторно:</label>
                </div>
                <div class="col-75">
                    <input type="password" name="passwordCheck"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label>Тип музыки</label>
                </div>
                <div class="col-75">
                    <select multiple="true" id="musicType" class="select_music" name="music"></select>
                </div>
            </div>
            <div class="button-create">
                <input name="createButton"  type="submit" value="create user" onclick="return validate(this.form)"/>
            </div>
        </div>
    </form>

</div>
</body>
</html>
