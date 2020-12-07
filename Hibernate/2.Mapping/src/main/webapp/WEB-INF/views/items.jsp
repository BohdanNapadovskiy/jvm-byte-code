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
    <c:set var="userID"><c:out value="${userID}" /></c:set>
    <title>Items</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">

    <script> <%@include file="/js/createTable.js"%> </script>

    <script language="JavaScript" type="text/javascript">

        function showElem(contId, value) {
            var container = document.getElementById(contId);

            if (value !=1) {
                container.style.display = 'none';
            } else {
                container.style.display = 'block';
                fillSelect(contId, value);
            }
        }
    </script>

    <script language="JavaScript" type="text/javascript">
        
        function sendFilter() {
            var filterName = $("#filter option:selected").text();
            var autoFilter = $("#container option:selected").text();
            $("#bodyTable").empty();
            $.ajax ({
                url: "userServlet",
                type: "get",
                success: function (items) {
                    createTable(items.userId, filterName, autoFilter);
                }
            });
        }
    
    </script>


</head>
<body>
<input id="userId" hidden value="">
<div class="mainDiv">

    <div class="w3-row-padding">
        <div class="w3-third">
            <select class="w3-select w3-border" name="filter" id="filter" onchange="showElem('container', this.value)">
                <option value="" disabled selected>Choose your option</option>
                <option value="1">By car</option>
                <option value="2">With photo</option>
                <option value="3">Current date</option>
            </select>
        </div>
        <div class="w3-third">
            <select class="w3-select w3-border" name="option" id="container">
            </select>
        </div>
        <div class="w3-third">
            <input type="button" value="find" onclick="sendFilter()">
        </div>
    </div>

    <table id="usersHeader">
        <caption>Items</caption>

        <thead>
        <tr>
            <th scope="col">Car</th>
            <th scope="col">Transmission</th>
            <th scope="col">Engine</th>
            <th scope="col">GearBox</th>
            <th scope="col">Sold</th>
            <th scope="col">DateOfCreate</th>
            <th></th>
        </tr>
        <tbody id="bodyTable">
        </tbody>
        </thead>
    </table>
    <div>
        <form action="${pageContext.servletContext.contextPath}/createItem" method="get">
            <input type="submit" value="Добавить новую заявку">
        </form>
    </div>


</div>
</body>

</html>