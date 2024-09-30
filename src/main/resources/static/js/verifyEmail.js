// 	VERIFYEMAIL JS 

$(document).ready(function() {

	let redirectModal = document.querySelector("#redirectModal"); 
	let secondElem = document.querySelector("#s"); 
	let token = document.querySelector("#token").value; 
	let submitBtn = document.querySelector("#verifyForm button"); 
	let isProcessing = false; 

	function verifyEmail(e) { 
	
		e.preventDefault(); 
	
		if(isProcessing) { return; } 
		isProcessing = true; 
	
		fetch(`/verifyToken?token=${token}`, { method: "GET" })
		.then(response => response.json())
		.then(data => { 
			
			let redirectInterval = function(elem) {
				
				let dur = 10; 
				
				let _interval = window.setInterval(function() { 
					
					dur -= 1; 
					elem.textContent = dur; 
					if(dur < 1) {
						
						window.clearInterval(_interval); 
						window.location.href = data.redirectURL; 
						
					} 
					
				}, 1000); 
				
			}; 
			
			$(redirectModal).modal("show"); 
			redirectModal.querySelector("#redirectModalLabel").textContent = data.message; 
			redirectModal.querySelector("a").setAttribute("href", data.redirectURL); 
			redirectModal.querySelector("#bodyText1").textContent = data.bodyText.split(", ")[0]; 
			redirectModal.querySelector("#bodyText2").textContent = data.bodyText.split(", ")[1]; 
			redirectInterval(secondElem); 
			isProcessing = false; 
	
		})
		.catch(error => { 
			
			console.error("Error:", error);
			isProcessing = false; 
	
		}); 
	
	} 

	submitBtn.addEventListener("click", verifyEmail); 
	submitBtn.addEventListener("touchend", verifyEmail); 

}); 