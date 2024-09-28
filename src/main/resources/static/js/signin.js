// 	SIGNIN JS 

$(document).ready(function() {
	
	const urlParams = new URLSearchParams(window.location.search); 
	let paramBool;
	if(urlParams.get("verified") !== null) {
		
		paramBool = urlParams.get("verified") == "true" ? true : false; 
		
	} 
	
	$("#signInModal").modal(paramBool ? "show" : "hide"); 
	$("#signupModal").modal(paramBool ? "hide" : "show"); 
	
	$("#signInForm").on("submit", function(ev) {
		
		ev.preventDefault(); 
		
		let loginData = {
			username: $("#signInForm input[name='username']").val(), 
			password: $("#signInForm input[name='password']").val() 
		}; 
		
		let csrfToken = document.querySelector("meta[name='_csrf']").getAttribute("content"); 
		let csrfHeaderName = document.querySelector("meta[name='_csrf_header']").getAttribute("content"); 
		
		$.ajax({
			type: "POST", 
			url: "/perform_login", 
			data: loginData, 
			contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
			headers: { [csrfHeaderName]: csrfToken }, 
			success: function(response) { 
				
				if(response != null) {
					
					console.log(response); 
					
				}
				
				if(response.success) {
					
					let successString = "Valid user credentials entered; login successful!"; 
					console.log(successString); 
					alert(successString); 
					
					$("#authenticatedUser i").text("Welcome " + response.username); 
					$("#signInModal").modal("hide"); 
					
				} 
				else {
					
					let failString = "Invalid user credentials entered; login unsuccessful!"; 
					console.log(failString); 
					alert(failString); 
					
				}
				
			}, 
			error: function(xhr, status, error) {
				
				let jsonResponse = JSON.parse(xhr.responseText); 
				console.log(jsonResponse.message); 
				
			}
		}); 
		
	}); 
	
}); 