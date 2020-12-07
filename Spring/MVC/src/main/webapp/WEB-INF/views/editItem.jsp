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
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.12.4/js/bootstrap-select.min.js"></script>

    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
    <script> <%@include file="/js/create.js"%> </script>


    <title>EditItem</title>
</head>

</body>
<div>
    <form action="${pageContext.servletContext.contextPath}/editItem" method="post" >
        <input type="hidden" name ="itemId" value="${editItem.itemId}">
        <input type="hidden" name ="carId" value="${editItem.car.carId}">
        <br >

        <div>
            <label> Название автомобиля: </label>
            <input class="w3-input w3-border" type="text" name ="car_name" value="${editItem.car.carName}"/>
        </div>
        <div class = "Engine-field">
            <label> Тип двигателя: </label>
            <select id="select_engine" class="w3-select" name="select_engine"></select>

        </div>
        <div class = "GearBox-field">
            <label> Коробка передач: </label>
            <select id="select_GearBox" class="w3-select" name="select_GearBox"></select>
        </div>
        <div class = "Transmission-field">
            <label> Ходовая часть: </label>
            <select id="select_Transmission" class="w3-select" name="select_Transmission"></select>
        </div>

        <div class="Status">
            <label> Статус заявки: </label>
            <select id="status_Item" class="w3-select" name="status_Item"></select>
        </div>

        <br>

        <div class="Images">
            <img src="${editItem.car.images.imagePath}"  name="foto" class="w3-border w3-padding" alt="foto">
        </div>

        <br>
        <div class="w3-btn w3-black">
            <input name="editButton" class="w3-btn w3-black" type="submit" value="Изменить заявку"/>
        </div>



    </form>
    <div class="w3-btn w3-black">
        <form Name ="back" action="${pageContext.servletContext.contextPath}/" method="get">
            <input type="submit" class="w3-btn w3-black" value="<-back">
        </form>
    </div>
</div>
</body>

</html>
