// 	TOINDEXLOGINSIGNUP JS 

$(document).ready(function() {
	
	let headerSignInBtn = document.querySelector("#headerSignInBtn"); 
	let headerSignupBtn = document.querySelector("#headerSignupBtn"); 
	
	$(headerSignInBtn).on("click touchend", function() {
		
		window.location.href = "/index?verified=true"; 
		
	}); 
	
	$(headerSignupBtn).on("click touchend", function() {
		
		window.location.href = "/index?verified=false"; 
		
	}); 
	
}); 