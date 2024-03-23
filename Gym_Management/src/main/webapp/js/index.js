function validate() {
    if (document.getElementById('uname').value=='') {
        alert("Please Enter User Name..");
        return false;
    }
    if (document.getElementById('pass').value=='') {
        alert("Please Enter Password..");
        return false;
    }
}