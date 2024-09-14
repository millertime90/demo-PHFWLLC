// 	SIGNIN JS 

$(document).ready(function() {
	
	$("#signInForm").on("submit", function(ev) {
		
		ev.preventDefault(); 
		
		let loginData = {
			username: $("input[name='username']").val(), 
			password: $("input[name='password']").val() 
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
				
				if(response.success) {
					
					let successString = "Valid user credentials entered; login successful!"; 
					console.log(successString); 
					alert(successString); 
					window.location.href = "/index?loginSuccess=true"; 
					
				} 
				else {
					
					let failString = "Invalid user credentials entered; login unsuccessful!"; 
					console.log(failString); 
					alert(failString); 
					window.location.href = "/index?loginError=true"; 
					
				}
				
			}, 
			error: function(xhr, status, error) {
				
				let jsonResponse = JSON.parse(xhr.responseText); 
				console.log(jsonResponse.message); 
				
			}
		}); 
		
	}); 
	
}); 