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
        $(document).ready(function () {
            $(function () {
                $("#country").change( function () {


                })
                // $("#country").autocomplete({
                //     source:function (request, response) {
                //         $.ajax({
                //             url: "countryJson",
                //             type: "GET",
                //             data: {
                //                 term: request.term
                //             },
                //             dataType : "json",
                //             success: function (data) {
                //                 response(data);
                //
                //             }
                //         });
                //     }
                // });
            });

        });

    </script>
    <script> <%@include file="/js/validate.js"%>  </script>

    <script type="text/javascript">
        $(document).ready(function() {selectCountry()})
    </script>

    <title>Title</title>
</head>
</body>

<div>
    <form action="${pageContext.servletContext.contextPath}/create" method="post">
        <div class = "create">
            <div class = "name-field">
                <label> Имя: </label>
                <input type="text" name ="name"/>
            </div>
            <div class = "login-field">
                <label> Логин: </label>
                <input type="text" name="login"/>
            </div>
            <div class = "email-field">
                <label> E-mail: </label>
                <input type="text" name="email"/>
            </div>
            <div class = "role-field">
                <label> Роль: </label>
                <select name="role">
                    <option value="user">User</option>
                    <option value="Admin">Admin</option>
                </select>
            </div>

            <div class="password-field">
                <label> Password:</label>
                <input type="password" name="password"/>
            </div>
            <div class="check-password">
                <label> Password again:</label>
                <input type="password" name="passwordCheck"/>
            </div>
            <%--<div>--%>
                <%--<label> Country</label>--%>
                <%--<input type="text" name="country" id ="country" />--%>
            <%--</div>--%>
            <div class="form-group col-md-6">
                <label>Country</label>
                <%--<select id="select_country" class="form-control" name="country" onfocus="selectCountry()" onchange="selectCity()"></select>--%>
                <select id="select_country" class="form-control" name="country" onfocus="selectCountry()"></select>
            </div>

            <%--<div class = "country-field">--%>
                <%--<label for="country">Country</label>--%>
                <%--<select class="selectCountry" name="country" id ="country" required></select>--%>
            <%--</div>--%>
            <%--<div class = "city-field">--%>
                <%--<label for="city">City</label>--%>
                <%--<select class="selectCity" name="city" id ="city" required></select>--%>
            <%--</div>--%>

            <%--<div class="col-md-5">--%>
                <%--+                            <label for="country">Country</label>--%>
                <%--+                            <select class="selectpicker" name="country" id="country" required></select>--%>
                <%--+                        </div>--%>
            <%--+                        <div class="col-md-4">--%>
            <%--+                            <label for="city">City</label>--%>
            <%--+                            <select class="selectpicker" name="city" id="city" required></select>--%>
            <%--+                        </div>--%>


            <div class="button-create">
                <input name="createButton"  type="submit" value="create user" onclick="return validate(this.form)"/>
            </div>


        </div>
    </form>
    <div class="button-back">
        <form Name ="back" action="${pageContext.servletContext.contextPath}/" method="get">
            <input type="submit" value="<-back">
        </form>
    </div>
</div>
</body>
</html>
