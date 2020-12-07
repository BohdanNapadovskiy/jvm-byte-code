<%--
  Created by IntelliJ IDEA.
  User: Bogdan
  Date: 04.07.2018
  Time: 21:23
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Users</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <style> <%@include file="/css/style.css"%> </style>



    <script>
        $(document).ready(function (){
            $.ajax ({
                url: "userJson",
                type: "get",
                success: function (data) {
                    var usersTable = $("#usersBody");
                    var newRows = "";
                    $.each(data, function (i, value) {
                        var button = "<td> <form action=\"${pageContext.servletContext.contextPath}/edit\" method=\"get\">\n" +
                            "                <input type=\"submit\" value=\"edit\">\n" +
                            "                <input type=\"hidden\" name = \"userId\" value="+value.id+"> " +
                            "                <input type=\"hidden\" name = \"roleId\" value="+value.role.role_id+"></form></td>"
                        newRows += "<tr><td>" + value.name +"</td>"+
                            "<td>" + value.login + "</td>" +
                            "<td>" + value.password + "</td>" +
                            "<td>" + value.role.user_role + "</td>" +
                            "<td>" + value.address.address_name + "</td>" +
                            "<td><ul id="+value.name+"type"+"></ul></td>"+
                            getMusictype(value);
                        newRows += button+
                            "</tr>";
                        $("#bodyTable").append(newRows);
                        newRows = "";
                    });
                }
            });
        });
    </script>
    <script>
        function getMusictype(user) {
            $.ajax ({
                url: "musicJson",
                type: "post",
                data: {
                    userId: user.id,
                },
            success: function (data) {
                    var musicType = $("#"+user.name+"type");
                    $.each(data, function (i, value) {
                        musicType.append($("<li></li>").val(value.music_name).html(value.music_name));
                    });
                }
            });
        }
    </script>

    <script>
        $(document).ready(function(){
            $("#searchRow").on("keyup", function() {
                var value = $(this).val().toLowerCase();
                $("#bodyTable tr").filter(function() {
                    $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
                });
            });
        });
    </script>

</head>
<body>
<div class="mainDiv">
    <div>
        <div>
            <p>Search:</p>
        </div>
        <div>
            <input id="searchRow" type="text" placeholder="Search..">
        </div>
    </div>
    <br><br>
    <table id="usersHeader">
        <caption>Users</caption>
        <thead>
        <tr>
            <th scope="col">Name</th>
            <th scope="col">Login</th>
            <th scope="col">password</th>
            <th scope="col">role</th>
            <th scope="col">Address</th>
            <th scope="col">MusicType</th>
            <th>editButon</th>
        </tr>
        <tbody id="bodyTable">

        </tbody>
        </thead>
    </table>
    <div>
        <form action="${pageContext.servletContext.contextPath}/create" method="get">
            <input type="submit" value="Add new user">
        </form>
    </div>


</div>
</body>

</html>