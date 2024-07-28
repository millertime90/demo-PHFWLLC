$(document).ready(function() {
	
	$(window).scroll(function() {
	
		let mediaOT = $(".contact-col .pt-1").offset().top,
			mediaOH = $(".contact-col .pt-1").outerHeight(),
			contactOT = $(".contact-col .pt-3").offset().top,
			contactOH = $(".contact-col .pt-3").outerHeight(),
			wh = $(window).height(),
			wst = $(window).scrollTop(); 
		
		if(wst > (mediaOT + mediaOH - wh)) {
		
			$(".contact-col .pt-1").removeClass("slideOutLeft").addClass("slideInLeft"); 
		
		} 
		else {

			$(".contact-col .pt-1").removeClass("slideInLeft").addClass("slideOutLeft"); 			
			
		} 
		
		if(wst > (contactOT + contactOH - wh)) {
			
			$(".contact-col .pt-3").removeClass("slideOutRight").addClass("slideInRight"); 
			
		} 
		else {
			
			$(".contact-col .pt-3").removeClass("slideInRight").addClass("slideOutRight"); 
			
		}
	
	}); 
	
}); 