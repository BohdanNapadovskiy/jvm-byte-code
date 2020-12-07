<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 11.08.2018
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Items</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <style> <%@include file="/css/style.css"%> </style>

    <script>
        function fillTable (){
            $(document).ready(function (){
                $.ajax ({
                    url: "itemsJson",
                    type: "get",
                    success: function (data) {
                        var checkValue = true;
                        if ($("#showAll").prop("checked")) {
                            checkValue = false;
                        }
                        $("#bodyTable tr").remove();
                        $.each(data, function (i, value) {
                            var newRows;
                            if (value.done == false ){
                                if (checkValue == false) {
                                    newRows += "<tr><td>" + value.desc +"</td>"+
                                        "<td>" + value.created + "</td>"+
                                        "<td><input id='doneCheck' type='checkbox' onclick='return false;' " + value.done + "/></td>" +
                                        "</tr>";
                                }
                            } else {
                                newRows += "<tr><td>" + value.desc +"</td>"+
                                "<td>" + value.created + "</td>"+
                                "<td><input id='doneCheck' type='checkbox' onclick='return false;' " + value.done + " checked/></td>" +
                                "</tr>";
                            }
                            $("#bodyTable").append(newRows);
                        });
                    }
                });
            });

        }
    </script>

</head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

<body onload="fillTable()">
<div class="mainDiv">
    <div class="createDiv" id="createDiv">
        <form action="${pageContext.servletContext.contextPath}/create" method="post">
            <div class="w3-row-padding">
                <div class="w3-half">
                    <label>Description</label>
                    <input class="w3-input w3-border" type="text" placeholder="One" name ="description">
                </div>
                <div class="w3-half">
                    <label>Date of item</label>
                    <input class="w3-input w3-border" type="date" placeholder="Two" name="createDate">
                </div>
                <input name="createItem" type="submit" value="Create Item">
            </div>
        </form>
    </div>

    <br>
    <br>
    <div class="check-box">
        <input id ="showAll" type="checkbox" name="doneValue" onchange="fillTable()" > Показать все <br>
    </div>

    <div id="table">
        <table id="itemsHeader">
            <caption>Items</caption>
            <thead>
            <tr>
                <th scope="col">Description</th>
                <th scope="col">Date</th>
                <th scope="col">done</th>
            </tr>
            <tbody id="bodyTable">
            </tbody>
            </thead>
        </table>
    </div>

</div>



</body>
</html>
