
const navbarMenu = document.querySelector(".navbar .links");
const menuBtn = document.querySelector(".menu-btn");
const hideMenuBtn = navbarMenu.querySelector(".close-btn");
const showPopupBtn = document.querySelector(".login-btn");
const hidePopupBtn = document.querySelector(".form-popup .close-btn");
const formPopup = document.querySelector(".form-popup");
const loginSignupLink = document.querySelectorAll(".form-box .bottom-link a");
const inputs = document.querySelectorAll("input");
const person = document.querySelector(".person");
const rocket = document.querySelector(".rocket");
const stars = document.querySelector(".stars");
const joinNowParent = document.querySelector(".btn-box")
// retrieve the button element with the class `btn`, within the parent .btn-box class. querySelector grabs only the first element that matches this selector.
const joinNowBtn = joinNowParent.querySelector("button.btn")


// show hamurbger menu contents
menuBtn.addEventListener("click", () => {

  navbarMenu.classList.toggle("show-menu");
});

// hide menu contents
hideMenuBtn.addEventListener("click", () => menuBtn.click());

// show popup form
showPopupBtn.addEventListener("click", () => {
	document.body.classList.toggle("show-popup");
	person.style.animationPlayState = "paused";
	rocket.style.animationPlayState = "paused";
	stars.style.animationPlayState = "paused";
	
});

// hide popup form
hidePopupBtn.addEventListener("click", () => {
	showPopupBtn.click();
	inputs.forEach(input => {
		input.value = "";
	});
	person.style.animationPlayState = "running";
	rocket.style.animationPlayState = "running";
	stars.style.animationPlayState = "running";
	// removes signup class after close.
	formPopup.classList.remove("show-signup");
});

// show popup form depening on link clicked (login vs signup)
loginSignupLink.forEach(link => {

  link.addEventListener("click", (e) => {

    e.preventDefault();

    formPopup.classList[link.id === "signup-link" ? 'add' : 'remove']("show-signup")
  })
})

// show sign-up form when the 'join now' button is clicked.
joinNowBtn.addEventListener("click", () => {
	formPopup.classList.add("show-signup");
	showPopupBtn.click();
})










