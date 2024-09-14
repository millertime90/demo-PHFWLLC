// 	MAIN JS 

$(document).ready(function() {

	let adModal = document.querySelector("#adModal"); 
	let signupModal = document.querySelector("#signupModal"); 
	let signinModal = document.querySelector("#signInModal"); 
	
	let adPopup = setInterval(function() {
		
		$(adModal).modal("show"); 
		clearInterval(adPopup); 
		
	}, 3000); 
	
	function toSignupModal() {
		
		$(signupModal).modal("show"); 
		
	}
	
	function toSigninModal() {
		
		$(signinModal).modal("show"); 
		
	}
	
	[
		document.querySelector("#adModal .btn-success"), 
		document.querySelector("#headerSignupBtn")
	]
	.forEach(i => {
		
		i.addEventListener("click", toSignupModal); 
		i.addEventListener("touchend", toSignupModal); 
		
	}); 
	
	let headerSignInBtn = document.querySelector("#headerSignInBtn"); 
	
	headerSignInBtn.addEventListener("click", toSigninModal); 
	headerSignInBtn.addEventListener("touchend", toSigninModal); 

	let cr = document.querySelector("#cr"); 
	let d = new Date(); 
	cr.innerHTML = cr.innerHTML + d.getFullYear(); 

}); 