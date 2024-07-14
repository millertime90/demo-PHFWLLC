// 	CAROUSELANIMATED JS 

$(document).ready(function() {

	let ccp = document.querySelector(".carousel-control-prev"); 
	let ccn = document.querySelector(".carousel-control-next"); 
	let h5s = document.querySelectorAll(".carousel-item h5"); 
	
	let hasClassName = (elems, className) => Array.from(elems).every(elem => elem.classList.contains(className)); 
	let slideInFromLeftClassName = "slideInFromLeft"; 
	let slideInFromRightClassName = "slideInFromRight"; 
	
	$(ccp).click(function() {
		
		if(hasClassName(h5s, slideInFromLeftClassName)) {
			
			$(h5s).removeClass(slideInFromLeftClassName)
				  .addClass(slideInFromRightClassName); 
			
		} 
		
	}); 
	
	$(ccn).click(function() {
		
		if(hasClassName(h5s, slideInFromRightClassName)) {
			
			$(h5s).removeClass(slideInFromRightClassName)
				  .addClass(slideInFromLeftClassName); 
			
		} 
		
	}); 

}); 