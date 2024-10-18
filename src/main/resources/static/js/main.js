// 	MAIN JS 

$(document).ready(function() {

	let adModal = document.querySelector("#adModal"); 
	let signupModal = document.querySelector("#signupModal"); 
	let signinModal = document.querySelector("#signInModal"); 
	let retrieveUsernameModal = document.querySelector("#retrieveUsernameModal"); 
	let sendResetPasswordLinkModal = document.querySelector("#sendResetPasswordLinkModal"); 
	
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
	
	function toRetrieveUsernameModal() {
		
		$(retrieveUsernameModal).modal("show"); 
		
	}
	
	function toPasswordResetModal() {
		
		$(sendResetPasswordLinkModal).modal("show"); 
		
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
	
	function retrieveUsername() {
		
		fetch("/forgot-username?emailAddress=" + encodeURIComponent($("#retrieveUsernameModal input[name='retrieveAtEmail']").val()), {
			method: "GET", 
			headers: { "Accept": "application/json" }
		})
		.then(response => {
			
			if(!response.ok) {
				
				return response.json()
							   .then(errorData => {
								   
								   throw new Error(errorData.message); 
								   
							   }); 
				
			}
			
			return response.json(); 
			
		})
		.then(data => {
			
			alert(data.username); 
			$("#retrieveUsernameModal input[name='retrieveAtEmail']").val(""); 
			
		})
		.catch(error => {
			
			alert(error.message); 	
			console.error(error, error.message); 
			
		}); 
		
	}
	
	function sendPasswordResetLink() {
		
		fetch("/forgot-password", {
			method: "POST", 
			headers: { "Accept": "application/json" },
			body: JSON.stringify({ 
				emailAddress: $("#sendResetPasswordLinkModal input[name='resetLinkToEmail']").val()
			})
		})
		.then(response => {
			
			if(!response.ok) {
				
				return response.json()
							   .then(errorData => {
								   
								   throw new Error(errorData.message); 
								   
							   }); 
				
			} 
			
			return response.json(); 
			
		})
		.then(data => {
			
			alert(data.message); 
			$("#sendResetPasswordLinkModal input[name='resetLinkToEmail']").val(""); 
			
		})
		.catch(error => {
			
			alert(error.message); 
			console.error(error, error.message); 
			
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
	let getRetrieveUsernameModal = document.querySelector("#getRetrieveUsernameModal");
	let getPasswordResetModal = document.querySelector("#getPasswordResetModal");  
	let retrieveUsernameBtn = document.querySelector("#retrieveUsernameBtn"); 
	let sendPasswordResetLinkBtn = document.querySelector("#sendPasswordResetLinkBtn"); 
	
	headerSignInBtn.addEventListener("click", toSigninModal); 
	headerSignInBtn.addEventListener("touchend", toSigninModal); 
	
	getRetrieveUsernameModal.addEventListener("click", toRetrieveUsernameModal); 
	getRetrieveUsernameModal.addEventListener("touchend", toRetrieveUsernameModal); 
	
	getPasswordResetModal.addEventListener("click", toPasswordResetModal); 
	getPasswordResetModal.addEventListener("touchend", toPasswordResetModal); 
	
	headerLogoutBtn.addEventListener("click", logout); 
	headerLogoutBtn.addEventListener("touchend", logout); 
	
	retrieveUsernameBtn.addEventListener("click", retrieveUsername); 
	retrieveUsernameBtn.addEventListener("touchend", retrieveUsername); 
	
	sendPasswordResetLinkBtn.addEventListener("click", sendPasswordResetLink); 
	sendPasswordResetLinkBtn.addEventListener("touchend", sendPasswordResetLink); 

	let cr = document.querySelector("#cr"); 
	let d = new Date(); 
	cr.innerHTML = cr.innerHTML + d.getFullYear(); 

}); 