
function isDigit() {
    return event.charCode >= 48 && event.charCode <= 57;
}

function validate() {
	var result = true;
	
	var login = document.getElementById("signUpForm").elements["login"];
	var loginError = document.getElementById("loginError");
	loginError.innerHTML = "";
	var loginRegExp = /^(?=.{8,20}$)(?![_])(?!.*[_]{2,})[a-zA-Z0-9_]+(?<![_])$/;
	if( !loginRegExp.test(login.value) ){
		login.focus();
		loginError.innerHTML = "Doesn't match pattern";
		result = false;
	}
	
	var password = document.getElementById("signUpForm").elements["password"];
	var passwordError = document.getElementById("passwordError");
	passwordError.innerHTML = "";
	var passwordRegExp = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
	if ( !passwordRegExp.test(password.value) ) {
		password.focus();
		passwordError.innerHTML = "Incorrect password";
		result = false;
	}
	
	var confirmedPassword = document.getElementById("signUpForm").elements["confirmedPassword"];
	var confirmedPasswordError = document.getElementById("confirmedPasswordError");
	confirmedPasswordError.innerHTML = "";
	if ( password.value != confirmedPassword.value ) {
		confirmedPassword.focus();
		confirmedPasswordError.innerHTML = "Not match";
		result = false;
	}
	
	var firstName = document.getElementById("signUpForm").elements["firstName"];
	var firstNameError = document.getElementById("firstNameError");
	firstNameError.innerHTML = "";
	var firstNameRegExp = /^([A-Za-z]{2,44})|([А-ЗІЙ-Яа-зій-яЎў']{2,44})|([А-Яа-я]{2,44})$/;
	if ( !firstNameRegExp.test(firstName.value) ) {
		firstName.focus();
		firstNameError.innerHTML = "Incorrect first name";
		result = false;
	}
	
	var lastName = document.getElementById("signUpForm").elements["lastName"];
	var lastNameError = document.getElementById("lastNameError");
	lastNameError.innerHTML = "";
	var lastNameRegExp = /^([A-Za-z]{2,44})|([А-ЗІЙ-Яа-зій-яЎў']{2,44})|([А-Яа-я]{2,44})$/;
	if ( !lastNameRegExp.test(lastName.value) ) {
		lastName.focus();
		lastNameError.innerHTML = "Incorrect last name";
		result = false;
	}
	
	var clientEmail = document.getElementById("signUpForm").elements["clientEmail"];
	var clientEmailError = document.getElementById("clientEmailError");
	clientEmailError.innerHTML = "";
	var clientEmailRegExp = /^(?=[a-z0-9@.!#$%&'*+\/=?^_`{|}~-]{6,254}$)(?=[a-z0-9.!#$%&'*+\/=?^_`{|}~-]{1,64}@)[a-z0-9!#$%&'*+\/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+\/=?^_`{|}~-]+)*@(?:(?=[a-z0-9-]{1,63}\.)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+(?=[a-z0-9-]{1,63}$)[a-z0-9](?:[a-z0-9-]*[a-z0-9])?$/;
	if ( !clientEmailRegExp.test(clientEmail.value) ) {
		clientEmail.focus();
		clientEmailError.innerHTML = "Incorrect email";
		result = false;
	}
	
	var phoneNumber = document.getElementById("signUpForm").elements["phoneNumber"];
	var phoneNumberError = document.getElementById("phoneNumberError");
	phoneNumberError.innerHTML = "";
	if ( phoneNumber.value.length > 0 && phoneNumber.value.length < 9) {
		phoneNumber.focus();
		phoneNumberError.innerHTML = "Incorrect phone number";
		result = false;
	}
	
	return result;
}