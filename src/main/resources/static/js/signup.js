// 	SIGNUP JS 

$(document).ready(function() {
	
	let signupBtn = document.querySelector("#signupBtn"); 
	let passwordField = document.querySelector("#signup-password"); 
	let passwordStrengthIndicatorContainer = document.querySelector("#passwordStrengthIndicator-container");
	let requirementIndicators = document.querySelectorAll(".requirementIndicator"); 
	
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
	
	// For password requirement indicators 
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
	
	// For field label requirement indicators 
	
	function haveSameClass(nodeList) {
				
		let bool = true; 
				
		nodeList.forEach(i => {
					
			if(!/fas fa-check-circle/.test(i.getAttribute("class"))) {
						
				bool = false; 
						
			} 
					
		}); 
				
		return bool; 
				
	} 
	
	function updateReq(e) {
		
		let reqIndicator = $(e.target).prev().find("i"); 
		
		if(e.target.name == "fname" || e.target.name == "lname" || e.target.name == "username") {
			
			if($(e.target).val().length > 0 && $(e.target).val().match(/^(?=.*[a-zA-Z]).*$/)) {
				
				reqIndicator.css("color", "green"); 
				reqIndicator.attr("class", "fas fa-check-circle"); 
				reqIndicator.html(""); 
				
			} 
			else {
				
				reqIndicator.css("color", "red"); 
				reqIndicator.removeClass("fas fa-check-circle"); 
				reqIndicator.html("*"); 
				
			}
			
		} 
		else if(e.target.name == "email") {
			
			if(/\.(com|net|org|edu|gov|mil|biz|info|mobi|name|aero|jobs|museum)$/.test($(e.target).val())) {
				
				reqIndicator.css("color", "green"); 
				reqIndicator.attr("class", "fas fa-check-circle"); 
				reqIndicator.html(""); 
				
			} 
			else {
				
				reqIndicator.css("color", "red"); 
				reqIndicator.removeClass("fas fa-check-circle"); 
				reqIndicator.html("*"); 
				
			}
			
		}
		else if(e.target.name == "password") {
			
			if(haveSameClass(requirementIndicators)) {
				
				reqIndicator.css("color", "green"); 
				reqIndicator.attr("class", "fas fa-check-circle"); 
				reqIndicator.html(""); 
				
			} 
			else {
				
				reqIndicator.css("color", "red"); 
				reqIndicator.removeClass("fas fa-check-circle"); 
				reqIndicator.html("*"); 
				
			}
			
		} 
		else {
			
			if($("#signup-password").val() == $("#signup-confirm_password").val()) {
				
				reqIndicator.css("color", "green"); 
				reqIndicator.attr("class", "fas fa-check-circle"); 
				reqIndicator.html(""); 
				
			} 
			else {
				
				reqIndicator.css("color", "red"); 
				reqIndicator.removeClass("fas fa-check-circle"); 
				reqIndicator.html("*"); 
				
			}
			
		} 
		
		if(haveSameClass(document.querySelectorAll("#signupForm label i"))) {
			
			$(signupBtn).prop("disabled", false); 
			
		}
		
	} 
	
	function resetRequirementIndicators() {
		
		$(requirementIndicators).removeClass("fas fa-check-circle");
		$(requirementIndicators).css("color", "red"); 
		$(requirementIndicators).html("*"); 
		$(document.querySelectorAll("#signupForm label i")).removeClass("fas fa-check-circle");
		$(document.querySelectorAll("#signupForm label i")).css("color", "red"); 
		$(document.querySelectorAll("#signupForm label i")).html("*"); 
		
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
	
	$("#signupForm input").on("input", updateReq); 
	$("#signupForm").on("submit", function(e) {
		
		e.preventDefault(); 
		
		let formData = {
			fname: $("#signupForm input[name='fname']").val(), 
			lname: $("#signupForm input[name='lname']").val(), 
			email: $("#signupForm input[name='email']").val(), 
			signUpUsername: $("#signupForm input[name='signUpUsername']").val(), 
			password: $("#signupForm input[name='password']").val(), 
			confirm_password: $("#signupForm input[name='confirm_password']").val() 
		}; 
		
		$.ajax({
			url: "/signup", 
			type: "POST", 
			contentType: "application/json", 
			data: JSON.stringify(formData), 
			success: function(response) { 
				
				console.log(response.message); 
				resetRequirementIndicators(); 
				$(passwordStrengthIndicatorContainer).css("visibility", "hidden"); 
				$("#psiText").html(""); 
				$(signupBtn).prop("disabled", true); 
				$(this)[0].reset(); 
				$("#signupModal").modal("hide"); 
				$("#responseEmail").text(response.email); 
				$("#emailVerificationModal").modal("show"); 
				
			}.bind(this), 
			error: function(xhr, status, error) {
				
				let response = JSON.parse(xhr.responseText); 
				
				alert(response.message); 
				
				console.error(error); 
				console.error(response.message); 
				
			}
			
		}); 
		
	}); 
	
}); 