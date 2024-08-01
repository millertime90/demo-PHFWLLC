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
	
	$("#adModal .btn-success, .signupBtn, #signupBtn").click(toSignupModal); 

	let cr = document.querySelector("#cr"); 
	let d = new Date(); 
	cr.innerHTML = cr.innerHTML + d.getFullYear(); 

}); 