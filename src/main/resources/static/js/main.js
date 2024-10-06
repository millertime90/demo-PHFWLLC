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
	
	function logout() {
		
		let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content"); 
		let csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content"); 
		
		fetch("/perform_logout", {
			method: "POST", 
			headers: { 
				"Content-Type": "application/x-www-form-urlencoded", 
				[csrfHeaderName]: csrfToken
			}
		})
		.then(response => {
			
			console.log(response.status); 
			if(response.ok) {
				
				window.alert("You are now logged out."); 
				$("#authenticatedUser i").text(""); 
				$("#headerSignInBtn").css({ display: "inline-block" }); 
				$("#headerLogoutBtn").css({ display: "none"}); 
				
			} 
			
		}); 
		
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
	let headerLogoutBtn = document.querySelector("#headerLogoutBtn"); 
	
	headerSignInBtn.addEventListener("click", toSigninModal); 
	headerSignInBtn.addEventListener("touchend", toSigninModal); 
	
	headerLogoutBtn.addEventListener("click", logout); 
	headerLogoutBtn.addEventListener("touchend", logout); 

	let cr = document.querySelector("#cr"); 
	let d = new Date(); 
	cr.innerHTML = cr.innerHTML + d.getFullYear(); 
	
	

}); 