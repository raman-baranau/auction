function validate(){
      
	if( document.loginForm.login.value == "" ){
	    alert( "Please provide your name!" );
	    document.loginForm.login.focus() ;
	    return false;
	}
 
	return( true );
}