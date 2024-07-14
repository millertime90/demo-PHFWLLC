// 	CAROUSELANIMATED JS 

$(document).ready(function() {

	let ccp = document.querySelector(".carousel-control-prev"); 
	let ccn = document.querySelector(".carousel-control-next"); 
	let h5s = document.querySelectorAll(".carousel-item h5"); 
	let lis = document.querySelectorAll(".carousel-indicators li"); 
	 
	let slideInFromLeftClassName = "slideInFromLeft"; 
	let slideInFromRightClassName = "slideInFromRight"; 
	
	let hasClassName = (elems, className) => Array.from(elems).every(elem => elem.classList.contains(className)); 
	
	// slideInFrom{Left/Right} class assigned as per carousel control button clicked 
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
	
	// slideInFrom{Left/Right} class assigned as per indicator li element clicked 
	lis.forEach(li => {
		
		li.addEventListener("click", e => {
			
			let activeLi = Array.from(lis).filter(i => i.classList.contains("active"))[0]; 
			let toBeActiveLi = Array.from(lis).filter(i => i == e.target)[0]; 
			
			if(Number(toBeActiveLi.dataset.slideTo) > Number(activeLi.dataset.slideTo)) {
				
				if(hasClassName(h5s, slideInFromRightClassName)) {
					
					$(h5s).removeClass(slideInFromRightClassName)
						  .addClass(slideInFromLeftClassName); 
					
				}
				
			} 
			else {
				
				if(hasClassName(h5s, slideInFromLeftClassName)) {
					
					$(h5s).removeClass(slideInFromLeftClassName)
						  .addClass(slideInFromRightClassName); 
					
				} 
				
			}
			
		}); 
		
	}); 

}); 