// 	BGIMGS JS 

$(document).ready(function() {
	
	let catContainer = document.querySelector(".cat-container"); 
	let ccOffsetTop = $(catContainer).offset().top; 
	let ccHeight = $(catContainer).height(); 
	let cc_85percDown = ccHeight * 0.85; 
	
	console.log("Top offset of categories container: " + ccOffsetTop); 
	console.log("Height of categories container: " + ccHeight); 
	console.log("85% of categories container height: " + cc_85percDown); 
	
	$(window).scroll(function() {
		
		let scroll = $(window).scrollTop(); 
		if(scroll >= ccOffsetTop && scroll <= ccOffsetTop + cc_85percDown) {
			
			let scrollPercent = (scroll - ccOffsetTop) / cc_85percDown; 
			
			$(".bgimg1").css({ width: (100 + scrollPercent * 100) + "%" }); 
			
		} 
		else if(scroll < ccOffsetTop) {
			
			$(".bgimg1").css({ width: "100%" }); 
			
		}
		
	}); 
	
}); 