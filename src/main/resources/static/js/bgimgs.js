// 	BGIMGS JS 

$(document).ready(function() {
	
	$(window).scroll(function() {
		
		let scroll = $(window).scrollTop(); 
		let windowHeight = $(window).height(); 
		
		$(".parallax").each(function() {
			
			let offsetTop = $(this).offset().top; 
			if(scroll > offsetTop - windowHeight && scroll < offsetTop + windowHeight) {
				
				let scale = 1 + (scroll + windowHeight - offsetTop) / (2 * windowHeight); 
				$(this).css("background-size", (scale * 100) + "%"); 
				
			}
			
		}); 
		
	}); 
	
}); 