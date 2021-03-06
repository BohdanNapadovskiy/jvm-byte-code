<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 11.04.2018
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/css/bootstrap-select.min.css">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>

    <script> <%@include file="/js/validate.js"%> </script>
    <style> <%@include file="/css/style.css"%> </style>

    <script>
        $(document).ready(function (){
            $("#country").empty();
            $.ajax ({
                url: "roleJson",
                type: "get",
                success: function (data) {
                    var roleTag = $("#role");
                    var user_role = "${role}";
                    $.each(data, function (i, value) {
                        if (user_role != value.user_role) {
                            roleTag.append($("<option></option>").val(value.user_role).html(value.user_role));
                        } else {
                            roleTag.append($("<option> </option>").val(value.user_role).html(value.user_role).attr("selected", true));
                        }
                    });
                }
            });
        });
    </script>
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

    <title>EditUser</title>
</head>
<body>

<div>
    <div class="button-back">
        <form action="${pageContext.servletContext.contextPath}/" method="get">
            <input type="submit" value="back">
        </form>
    </div>

    <form action="${pageContext.servletContext.contextPath}/edit"  method="post">
        <div class = "edit">
            <div class = "row">
                <div class="col-25">
                    <label> Имя: </label>
                </div>
                <div class="col-75">
                    <input type="text" value="${editUser.name}" name ="name"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Логин: </label>
                </div>
                <div class="col-75">
                    <input type="text" value="${editUser.login}" name="login"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Новый пароль:</label>
                </div>
                <div class="col-75">
                    <input type="password" name="password"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Address: </label>
                </div>
                <div class="col-75">
                    <input type="text"  value="${editUser.address.address_name}" name="address"/>
                </div>
            </div>
            <div class = "row">
                <div class="col-25">
                    <label> Роль: </label>
                </div>
                <div class="col-75">
                    <select name="role" id="role">
                    </select>
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

            <div class="button-edit">
                <input name="editButton"  type="submit" value="edit user" onclick="return validate(this.form)"/>
            </div>
            <input type="hidden" name="userId" value="${editUser.id}">
        </div>
    </form>
</div>

</body>
</html>
