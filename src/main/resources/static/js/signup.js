// 	SIGNUP JS 

$(document).ready(function() {
	
	let signupBtn = document.querySelector("#signupBtn"); 
	let passwordField = document.querySelector("#signup-password"); 
	let passwordStrengthIndicatorContainer = document.querySelector("#passwordStrengthIndicator-container");
	
	function updatePasswordStrengthIndicator(strength) {
		
		let passwordStrengthIndicator = document.querySelector("#passwordStrengthIndicator"); 
		let psiText = document.querySelector("#psiText"); 
		
		if(strength <= 20) {
			
			$(passwordStrengthIndicator).css("width", strength + "%"); 
			$(passwordStrengthIndicator).removeClass("bg-warning bg-info bg-success bg-successPlus").addClass("bg-danger");
			$(psiText).html(`Weak Password<br />Strong Password Required`);  
			
		} 
		else if(strength <= 40) {
			
			$(passwordStrengthIndicator).css("width", strength + "%"); 
			$(passwordStrengthIndicator).removeClass("bg-danger bg-info bg-success bg-successPlus").addClass("bg-warning"); 
			$(psiText).html(`Fair Password<br />Strong Password Required`); 
			
		} 
		else if(strength <= 60) {
			
			$(passwordStrengthIndicator).css("width", strength + "%"); 
			$(passwordStrengthIndicator).removeClass("bg-danger bg-warning bg-success bg-successPlus").addClass("bg-info"); 
			$(psiText).html(`Above Fair Password<br />Strong Password Required`); 
			
		} 
		else if(strength <= 80) {
			
			$(passwordStrengthIndicator).css("width", strength + "%"); 
			$(passwordStrengthIndicator).removeClass("bg-danger bg-warning bg-info bg-successPlus").addClass("bg-success");
			$(psiText).html(`Good Password<br />Strong Password Required`);  
			
		}
		else {
			
			$(passwordStrengthIndicator).css("width", strength + "%"); 
			$(passwordStrengthIndicator).removeClass("bg-danger bg-warning bg-info bg-success").addClass("bg-successPlus"); 
			$(psiText).html(`Strong Password<br />Strong Password Requirement met`); 
			
		}
		
	}
	
	function getPasswordStrength(password) {
		
		let strength = 0; 
		
		if(password.length >= 8) {
			
			strength += 20; 
			
		}
		
		if(password.match(/^(?=.*[a-z])/)) {
			
			strength += 20; 
			
		} 
		
		if(password.match(/^(?=.*[A-Z])/)) {
			
			strength += 20; 
			
		} 
		
		if(password.match(/[!@#$%^&*()_+\-=[\]{};':"\\/,.<>\/?]/)) {
			
			strength += 20; 
			
		} 
		
		if(password.match(/[0-9]/)) {
			
			strength += 20; 
			
		} 
		
		return strength; 
		
	} 
	
	function updateRequirementIndicators(password) {
		
		const requirements = [
			password.match(/^(?=.*[a-z])/), 
			password.match(/^(?=.*[A-Z])/), 
			password.length >= 8, 
			password.match(/[0-9]/), 
			password.match(/[!@#$%^&*()_+\-=[\]{};':"\\/,.<>\/?]/), 
			/^\S+$/.test(password) 
		]; 
		
		const requirementIndicators = $(".requirementIndicator"); 
		
		if(password === "") {
			
			requirementIndicators.removeClass("fas fa-check-circle"); 
			requirementIndicators.html("*"); 
			requirementIndicators.css("color", "red"); 
			
			return; 
			
		} 
		
		for(let i = 0; i < requirements.length; i++) {
			
			const iconColor = requirements[i] ? "green" : "red"; 
			const requirementIndicator = requirementIndicators.eq(i); 
			
			if(requirements[i]) {
				
				requirementIndicator.addClass("fas fa-check-circle"); 
				requirementIndicator.html(""); 
				requirementIndicator.css("color", iconColor); 
				
			} 
			else {
				
				requirementIndicator.removeClass("fas fa-check-circle"); 
				requirementIndicator.html("*"); 
				requirementIndicator.css("color", iconColor); 
				
			}
			
		}
		
	} 
	
	$(signupBtn).prop("disabled", true); 
	$(passwordField).on("input", function() {
		
		if($(passwordField).val().length > 0) {
			
			$(passwordStrengthIndicatorContainer).css("visibility", "visible"); 
			updatePasswordStrengthIndicator(getPasswordStrength($(passwordField).val())); 
			
		} 
		else {
			
			$(passwordStrengthIndicatorContainer).css("visibility", "hidden"); 
			$("#psiText").html(""); 
			
		} 
		
		updateRequirementIndicators($(passwordField).val()); 
		
	}); 
	
	$("#signupForm").on("submit", function(e) {
		
		e.preventDefault(); 
		
		let formData = {
			fname: $("#signupForm input[name='fname']").val(), 
			lname: $("#signupForm input[name='lname']").val(), 
			email: $("#signupForm input[name='email']").val(), 
			username: $("#signupForm input[name='username']").val(), 
			password: $("#signupForm input[name='password']").val(), 
			confirm_password: $("#signupForm input[name='confirm_password']").val() 
		}; 
		
		$.ajax({
			
			url: "/signup", 
			type: "POST", 
			contentType: "application/json", 
			data: JSON.stringify(formData), 
			success: function(response) {
				
				console.log("Response Entity:"); 
				for(let i in response) {
					
					console.log(i + ": " + response[i]); 
					
				}
				
				$(this)[0].reset(); 
				
			}.bind(this), 
			error: function(xhr, status, error) {
				
				console.error(error); 
				
			}
			
		}); 
		
	}); 
	
}); 