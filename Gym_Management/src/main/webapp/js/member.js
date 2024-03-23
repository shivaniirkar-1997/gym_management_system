function input() {
	/*if(!isNaN(document.getElementById('fname').value)){
		alert('Please Enter Only Character`s');
		return false;
	}
	if(!isNaN(document.getElementById('lname').value)){
		alert('Please Enter Only Character`s');
		return false;
	}*/
	
  if (isNaN(document.getElementById('con').value)) {
    alert('Please Enter Only Number');
    return false;
  }
  if (isNaN(document.getElementById('h').value)) {
    alert('Please Enter Height in Only Digit');
     return false;
  }
  if (isNaN(document.getElementById('w').value)) {
    alert('Please Enter Weight Only Digit');
     return false;
  }
   if (document.getElementById('con').value.lenght()==11) {
    alert('Please Enter Only 10 Digit Number');
     return false;
  }
}

function trainer()
{
	  if (isNaN(document.getElementById('tname').value)) {
      }else{
	 alert('Please Enter Only Character');
	  return false;
	}
	 if (document.getElementById('tname').value=='') {
    alert('Please Enter Trainer Name');
     return false;
  }
   if (isNaN(document.getElementById('tcon').value)) {
	 alert('Please Enter Only Number');
	  return false;
      }
}