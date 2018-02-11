function onSubmit() {
    var info = document.getElementById("info");
    info.parentNode.removeChild("info");
    
    var msg = document.getElementById("msg");
    msg.classList.add("message");
    msg.classList.add("active-message");
}