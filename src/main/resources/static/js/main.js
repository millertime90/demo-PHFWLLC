// 	MAIN JS 

$(document).ready(function() {

	let adModal = document.querySelector("#adModal"); 
	let signupModal = document.querySelector("#signupModal"); 
	
	let adPopup = setInterval(function() {
		
		$(adModal).modal("show"); 
		clearInterval(adPopup); 
		
	}, 3000); 
	
	function toSignupModal() {
		
		$(signupModal).modal("show"); 
		
	}
	
	[document.querySelector("#adModal .btn-success"), document.querySelector(".signupBtn")].forEach(i => {
		
		i.addEventListener("click", toSignupModal); 
		i.addEventListener("touchend", toSignupModal); 
		
	}); 

	let cr = document.querySelector("#cr"); 
	let d = new Date(); 
	cr.innerHTML = cr.innerHTML + d.getFullYear(); 

}); 