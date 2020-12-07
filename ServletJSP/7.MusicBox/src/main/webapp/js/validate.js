function validate(f) {
    var dis = true;

    if (f.name.value == "") {
        f.name.style.backgroundColor ="#d90000";
        dis = false;
    } else {
        f.name.style.backgroundColor ="#fbfbff";
    }
    if (f.login.value == "") {
        f.login.style.backgroundColor ="#d90000";
        dis = false;
    } else {
        f.login.style.backgroundColor ="#fbfbff";
    }
    if (f.email.value == "") {
        f.email.style.backgroundColor ="#d90000";
        dis = false;
    } else {
        f.email.style.backgroundColor ="#fbfbff";
    }

    if (f.password.value != "" && f.passwordCheck.value != "") {
        if (f.password.value != f.passwordCheck.value) {
            f.password.style.backgroundColor ="#d90000";
            dis = false;
        }
    } else if (f.password.value == "") {
        f.password.style.backgroundColor ="#d90000";
        dis = false;
    } else if (f.passwordCheck.value == "") {
        f.passwordCheck.style.backgroundColor ="#d90000";
        dis = false;
    } else {
        f.password.style.backgroundColor ="#fbfbff";
        f.passwordCheck.style.backgroundColor ="#fbfbff";
    }


    if (!dis) {
        alert("Error");
    }
    return dis;
}
