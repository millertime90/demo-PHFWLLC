// 	CAROUSEL JS 

let productCarousel = document.querySelector("#productCarousel"); 
let vidbtns = document.querySelectorAll(".demo"); 

let infoClassName = "btn-info"; 
let successClassName = "btn-success"; 

let checkBtnClass = (btn, classname) => btn.classList.contains(classname); 
let _css = (objs, props) => {
	
	for(let i = 0; i < objs.length; i++) {
		
		for(let prop in props) {
			
			objs[i].style[prop] = props[prop]; 
			
		}
		
	}
	
}; 

vidbtns.forEach(btn => {
	
	btn.addEventListener("click", e => {
		
		let vid = e.target.parentNode.querySelector(".vidclass video"); 
		
		if(checkBtnClass(e.target, infoClassName)) {
			
			e.target.classList.remove(infoClassName); 
			e.target.classList.add(successClassName); 
			e.target.textContent = "Hide Demo"; 
			
			productCarousel.setAttribute("data-interval", "false"); 
			vid.play(); 
			
			_css([e.target.parentNode.querySelector(".vidclass")], { display: "block" }); 
			_css([...e.target.parentNode.querySelectorAll("h5"), e.target.parentNode.querySelector("h3")], { display: "none" }); 
			
		} 
		else if(checkBtnClass(e.target, successClassName)) {
			
			e.target.classList.remove(successClassName); 
			e.target.classList.add(infoClassName); 
			e.target.textContent = "See Demo"; 
			
			productCarousel.setAttribute("data-interval", "10000"); 
			vid.pause(); 
			vid.currentTime = 0; 
			
			_css([e.target.parentNode.querySelector(".vidclass")], { display: "none" }); 
			_css([...e.target.parentNode.querySelectorAll("h5"), e.target.parentNode.querySelector("h3")], { display: "block" }); 
			
		}
		
	}); 
	
}); 