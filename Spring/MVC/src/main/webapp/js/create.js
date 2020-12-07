$(document).ready(function (){
    $.ajax ({
        url: "userServlet",
        type: "get",
        success: function (items) {
            fillEngine(items.userId);
            fillTransmision(items.userId);
            fillGearBox(items.userId);
            fillFotoAndStatus(items.userId);
        }
    });
});


function fillEngine (userId) {
    $.ajax({
        url: "jsonServlet",
        type: "get",
        data: {"namePart": "engine"},
        success: function (data) {
            var engineType = $("#select_engine");
            var engineName = "${editItem.car.engine.engineName}";
            engineType.append("<option value=\"\" disabled selected>Choose your option</option>");
            $.each(data, function (i, value) {
                if (engineName != value.engineName) {
                    engineType.append($("<option></option>").val(value.engineName).html(value.engineName));
                } else {
                    engineType.append($("<option> </option>").val(value.engineName).html(value.engineName).attr("selected", true));
                }


            });
        }
    });
}


function fillGearBox(userId) {
    $.ajax({
        url: "jsonServlet",
        type: "get",
        data: {"namePart": "gearBox"},
        success: function (data) {
            var gearBox = $("#select_GearBox");
            var gearBoxName = "${editItem.car.gearBox.gearBoxName}";
            gearBox.append("<option value=\"\" disabled selected>Choose your option</option>");
            $.each(data, function (i, value) {
                if (gearBoxName != value.gearBoxName) {
                    gearBox.append($("<option></option>").val(value.gearBoxName).html(value.gearBoxName));
                } else {
                    gearBox.append($("<option> </option>").val(value.gearBoxName).html(value.gearBoxName).attr("selected", true));
                }
            });
        }

    });
}

function fillTransmision(userId) {
    $.ajax({
        url: "jsonServlet",
        type: "get",
        data: {"namePart": "transmission"},
        success: function (data) {
            var trans = $("#select_Transmission");
            var transName = "${editItem.car.transmission.transName}";

            trans.append("<option value=\"\" disabled selected>Choose your option</option>");

            $.each(data, function (i, value) {
                if (transName != value.transName) {
                    trans.append($("<option></option>").val(value.transName).html(value.transName));
                } else {
                    trans.append($("<option> </option>").val(value.transName).html(value.transName).attr("selected", true));
                }
            });
        }

    });

}

function fillFotoAndStatus(userId) {
    var statusField  = $("#status_Item");
    var status = "${editItem.closed}";

    var fotoField = $("#foto");

    statusField.append("<option value=\"\" disabled selected>Choose your option</option>");

    if (status == "true") {
        statusField.append("<option value='Closed' selected true >Closed</option>");
        statusField.append("<option value='Open'>Open</option>");
    } else {
        statusField.append("<option value='Closed'>Closed</option>");
        statusField.append("<option value='Open' selected true >Open</option>");
    }

}