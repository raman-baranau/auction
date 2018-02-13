function isDigit() {
    return event.charCode >= 48 && event.charCode <= 57;
}

function validate1() {
    var result = true;
    
    var oldPassword = document.getElementById("editPasswordForm").elements["oldPassword"];
    var oldPasswordError = document.getElementById("oldPasswordError");
    oldPasswordError.className = "error";
    if ( oldPassword.value.length == 0 ) {
        oldPassword.focus();
        oldPasswordError.className += " visible";
        result = false;
    }
    
    var newPassword = document.getElementById("editPasswordForm").elements["newPassword"];
    var newPasswordError = document.getElementById("newPasswordError");
    newPasswordError.className = "error";
    var passwordRegExp = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
    if ( !passwordRegExp.test(newPassword.value) ) {
        newPassword.focus();
        newPasswordError.className += " visible";
        result = false;
    }
    
    var confirmedPassword = document.getElementById("editPasswordForm").elements["confirmedNewPassword"];
    var confirmedPasswordError = document.getElementById("confirmedNewPasswordError");
    confirmedPasswordError.className = "error";
    if ( newPassword.value != confirmedPassword.value ) {
        confirmedPassword.focus();
        confirmedPasswordError.className += " visible";
        result = false;
    }
    
    return result;
}


function validate2() {
    var result = true;
    
    var clientEmail = document.getElementById("editInfoForm").elements["clientEmail"];
    var clientEmailError = document.getElementById("clientEmailError");
    clientEmailError.className = "error";
    var clientEmailRegExp = /^(?=[a-z0-9@.!#$%&'*+\/=?^_`{|}~-]{6,254}$)(?=[a-z0-9.!#$%&'*+\/=?^_`{|}~-]{1,64}@)[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:(?=[a-z0-9-]{1,63}\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?=[a-z0-9-]{1,63}$)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
    if ( !clientEmailRegExp.test(clientEmail.value) ) {
        clientEmail.focus();
        clientEmailError.className += " visible";
        result = false;
    }
    
    var phoneNumber = document.getElementById("editInfoForm").elements["phoneNumber"];
    var phoneNumberError = document.getElementById("phoneNumberError");
    phoneNumberError.className = "error";
    if ( phoneNumber.value.length > 0 && phoneNumber.value.length < 9) {
        phoneNumber.focus();
        phoneNumberError.className += " visible";
        result = false;
    }
    
    return result;
}