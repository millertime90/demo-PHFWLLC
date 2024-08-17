// 	VERIFYEMAIL JS 

let token = document.querySelector("#token"); 
let submitBtn = document.querySelector("#verifyForm button"); 
let isProcessing = false; 

function verifyEmail() { 
	
	if(isProcessing) { return; } 
	isProcessing = true; 
	
	fetch(`/verifyToken?token=${token}`, { method: "GET" })
	.then(response => response.text())
	.then(data => { 
		alert(data);
		isProcessing = false; 
	})
	.catch(error => { 
		console.error("Error:", error);
		isProcessing = false; 
	}); 
	
} 

submitBtn.addEventListener("click", verifyEmail); 
submitBtn.addEventListener("touchend", verifyEmail); 