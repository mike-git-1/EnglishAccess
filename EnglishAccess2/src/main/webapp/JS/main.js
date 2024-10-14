/**
 * 
 */
const navbarMenu = document.querySelector(".navbar .links");
const menuBtn = document.querySelector(".menu-btn");
const hideMenuBtn = navbarMenu.querySelector(".close-btn");
// source element of the audio element
const audio = document.getElementById("source");
// the audio element
const audioElement = document.querySelector(".audioPlayer");

// retrieve the 'add new note' button that will trigger the popup to add a note
const addBox = document.querySelector(".add-box"), 
// retrieve the shaded overlay container
popupBox = document.querySelector(".popup-box"),
// retrieve the form title
popupTitle = popupBox.querySelector(".note-header p"),
// retrieve the close icon
closeIcon = popupBox.querySelector(".note-header i"),
// retrieve the user inputted title for the note
titleTag = popupBox.querySelector("input"),
// retrieve he user inputted description for the note
descTag = popupBox.querySelector("textarea"),
// retrieve the add button form the form
addBtn = popupBox.querySelector("button");

//for timestamp
const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul","Aug","Sept","Oct","Nov","Dec"];
// used to check if a note was udpated
// isUpdate = false and a new updateId variable is declared
let isUpdate = false, updateId;

// show hamurbger menu contents
menuBtn.addEventListener("click", () => {

  navbarMenu.classList.toggle("show-menu");
});

// hide menu contents
hideMenuBtn.addEventListener("click", () => menuBtn.click());


// ====================================================================
// ATTEMPT
// ====================================================================


// Event listener for search button. When the button is clicked, call the 'define' function
document.getElementById("definition").addEventListener("click", define, false);

function define() {
	

  const meaningSection = document.getElementById("meanings-section");
  // word to be defined. From user input
  const word = document.getElementById("define").value;

  // initiate http request to fetch data from dictionary API
  fetch('https://api.dictionaryapi.dev/api/v2/entries/en/' + word)
  .then(res => {
      // check the response. if not ok, output an error.
      if (!res.ok) {
      throw new Error('Network response was not ok');
      }
      // otherwise, convert it to json format.
      return res.json();
  })
  // handle the json response data, 'data'
  .then(data => {
	meaningSection.innerHTML = `
		<section class="word">
			<h3> ${word} </h3>
			<button onclick="playAudio()">
				<i class="fa-solid fa-volume-high"></i>
		</section>`;


    // Searches through the phonetics array to find the first available audio URL for pronunciation.
    let audioUrl = null;
    for (let phonetic of data[0].phonetics) {
      // if one is found, set it = audioURL
      // audio = property of phonetic array that holds the audio URL
      if (phonetic.audio) {
        audioUrl = phonetic.audio;
        break;
      }
    }
	
    // If a valid audio URL is found, set it and play
    if (audioUrl) {
      // set the src attribute on the audio element
	  // audioElement defined earlier as the audio element
      audioElement.src = audioUrl;
      audioElement.load(); // Load the new audio source
    } else {
      console.log("No valid audio source found.");
    }

	
    // The following is a representation of the json object:
    // 0:
    //  phonetics: 
    //    0:
    //      audio:
    //  meanings:
    //    0:
    //      partOfSpeech:
    //      defintions:
    //        0:
    //        1: etc..
    //      synonyms:
    //      antonyms:
    //      examples: (does not exist most of the time)
    //    1:
    //    2: etc..
    // data[0].meanings = grabs the array with keyword 'meanings' from the json object. 
    // Each  'meanings' (0,1,2) is represented by variable 'meanings'. 
    for (let meanings of data[0].meanings) {
      
		meaningSection.innerHTML += `
	 	<div class="details">
			<!-- meanings.partOfSpeech retrieves the partOfSpeech property of each meaning obj-->
			<!-- whereas data[0].meanings.partOfSpeech attempts to retrieve the partOfSpeech property of the entire meaning array which is incorrect-->
 			<p>${meanings.partOfSpeech}</p>
     		<p>${data[0].phonetic}</p>
  		</div>`;
	  

      // retrieve the definitions property[], and for each definition in the definitions property [0,1,2..], do this...
     	meanings.definitions.forEach(definition =>{
	        // create a regex to look for the searched word. g = search globally for all occurences of the word in the definition. i = case-insensitive.
	        const regex = new RegExp(`\\b${word}\\b`, 'gi');
	        // using the regex, replace occurences of the word with a highlighted version:
			// for definitions:
	        const highlightedDefinition = definition.definition.replace(regex, `<span class="highlightdefine">${word}</span>`);
			if (definition.example) {
				// for examples:
				const highlightedExample = definition.example.replace(regex, `<span class="highlightdefine">${word}</span>`);
				// adding it all together
				meaningSection.innerHTML += `
					<div class="word-definition">
						<p>${highlightedDefinition}</p>
						<p class="example-bar">${highlightedExample || ""}</p>
					</div>`;	
			} else {
				meaningSection.innerHTML += `
					<div class="word-definition">
						<p>${highlightedDefinition}</p>
					</div>`;
			};
		});
     };
	})

  // Catches any errors that occur during the fetch operation and logs them. E.g. examples not existing.
  // Clears the example element’s text in case of an error.
	.catch(error => {
      console.error('Fetch error:', error);
      meaningSection.innerHTML = `<h3 class ="error">We couldn't seem to find the word... Mabye try a different spelling? </h3>`;
 	 });
};

function playAudio() {
   audioElement.play();
}
	
// =======================================================================
// END ATTEMPT
// =======================================================================


/*
var button = document.getElementById("definition").addEventListener("click", define, false);
const meaningSection = document.getElementById("parts-of-speech");

function define() {


    while (meaningSection.firstChild && meaningSection.firstChild.tagName !== 'H2') {
        meaningSection.removeChild(meaningSection.firstChild);
    }
    const word = document.getElementById("define").value;
    var meaning = document.getElementById("meaning");
    var example = document.getElementById("example");
    let audio = document.getElementById("source");
    let audioElement = document.querySelector("audio");

    fetch('https://api.dictionaryapi.dev/api/v2/entries/en/' + word)
        .then(res => {
            if (!res.ok) {
                throw new Error('Network response was not ok');
            }
            return res.json();
        })
        .then(data => {
            for (let meanings of data[0].meanings) {
                let parent = document.getElementById("parts-of-speech");
                let newElement = document.createElement('h3');
                newElement.style.backgroundColor = "green";
                newElement.style.width = "max-content";
                newElement.style.textAlign = "left";
                newElement.style.padding = "10px";
                newElement.style.margin= "0px";
                newElement.style.color = "white";
                
                parent.appendChild(newElement);
				newElement.innerText = `${word}`;
                //newElement.innerText = meanings.partOfSpeech;

                meanings.definitions.forEach(definition =>{
                    const regex = new RegExp(`\\b${word}\\b`, 'gi');
                    const highlightedDefinition = definition.definition.replace(regex, `<span class="highlightdefine">${word}</span>`);
                    
                    let definitionText = document.createElement("p");
                    definitionText.innerHTML = `Definition: ${highlightedDefinition}`;
                    parent.appendChild(definitionText);
                    
                })

                meanings.definitions.forEach(definition =>{
                    const regex = new RegExp(`\\b${word}\\b`, 'gi');
                    const highlightedExample = definition.example.replace(regex, `<span class="highlightdefine">${word}</span>`);

                    let exampleText = document.createElement("p");
                    exampleText.classList.add('example');
                    exampleText.innerHTML = `Example: ${highlightedExample}`;
                    parent.appendChild(exampleText);
                    }
                
            )
            

            let audioUrl = null;
            for (let phonetic of data[0].phonetics) {
                if (phonetic.audio) {
                    audioUrl = phonetic.audio;
                    break;
                }
            }

            // If a valid audio URL is found, set it and play
            if (audioUrl) {
                audio.src = audioUrl;
                audioElement.load(); // Load the new audio source
                audioElement.play(); // Play the audio automatically
            } else {
                console.log("No valid audio source found.");
            }
        }})
        .catch(error => {
            console.error('Fetch error:', error);
            example.innerText = "";
           
        });
};*/

//This function is triggered on the onclick event of the logout button

function logout(){
	// the function sends a post request to the /Logout URL handled by the logout servlet.
	// The servlet handles the request by invalidating the user session and preparing the response.
	fetch('Logout', {
		method:'POST', 
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	})
	// if response from server is successful, redirect the user to index.jsp 
	.then(response =>{
		if (response.ok){
			window.location.href = 'index.jsp';
			
		} else {
			console.error('Logout failed');
		}
	})
	.catch(error =>{
		console.error('Error:', error);
	})
}

// performs a GET request by default if method is not specified
function loadNotes() {

	document.querySelectorAll(".note").forEach(note => note.remove());
	
    fetch('fetch_notes')
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok ' + response.statusText);
            }
			// if response successful, return the response as json
            return response.json();
        })
        .then(notes => {
			// reverses the order of notes in the notes array so that the most recent notes are displayed at the top.
			notes.reverse(); 
            // Process the notes (the json response)
			// `note` represents each note object from the JSON array
            notes.forEach(note => {
				// Retrieve the date string from each note and Convert it to JavaScript Date object
				let noteDate = new Date(note.date);
				// extract the month, day, and year from the date object
				let month = months[noteDate.getMonth()],
				    day = noteDate.getDate(),
				    year = noteDate.getFullYear();
				const noteid = note.noteID;
				
				// render the HTML for each note	
				// the key names correspond to the names defined in the note bean class. Keys are case-sensitive
	            let liTag = `<li class="note">
	                <div class="details">
	                  <p>${note.title}</p>
	                  <span>${note.noteContent}</span>
	                </div>
	                <div class="bottom-content">
	                  <span>${`${month} ${day}, ${year}`}</span>
	                  <div class="settings">
	                    <i onclick="showMenu(this)" class="uil uil-ellipsis-h"></i>
	                    <ul class="menu">
	                      <li onclick="updateNote(this)"><i class="uil uil-pen"></i>Edit</li>
						  <! -- used single notes for within the onclick attribute to avoid conflicts with the surrounding double quotes used in the template literal.-->
	                      <li onclick="remove_note('${noteid}', this)"><i class="uil uil-trash"></i>Delete</li>
	                    </ul>
	                  </div>
	                </div>
	              </li>`;
	            addBox.insertAdjacentHTML("afterend", liTag);
            });
        })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

// Call loadNotes when the page loads
// loadNotes() function is called only after the DOM has been fully loaded and parsed
document.addEventListener('DOMContentLoaded', loadNotes);

//Sends a POST request to the /add_note URL with the note content, handled dby the add_note servlet
function add_note(){
		const noteTitle = document.querySelector(".title input").value;
		const noteContent = document.querySelector(".description textarea").value;
	    //const noteContent = document.getElementById("note-text").value;
		//const noteSection = document.getElementById("notes-section");


	
	
	fetch('add_note', {
		method:'POST', 
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams({
			title: noteTitle,
			content: noteContent
		})
		
	})
	/* if response is successful, return the response text as a string defined below
	.then(response =>{
		if (response.ok){
			console.log("note-successfully-added");
			return;
		}
		
		else{
			console.error("note-not-successfully-added");
		}
	})*/
	
	
	.then(response =>{
		if (response.ok){
			console.log("note-successfully-added");
			// refresh the notes list with the newly added note
			loadNotes();		
			
			
			/*const note = document.getElementById("note-text").value;
					    let singleNote = document.createElement("p");
					    singleNote.setAttribute("class", "single-note");
					    noteSection.appendChild(singleNote);
					    singleNote.innerText = text;
					    let icon = document.createElement("span");
					    icon.setAttribute("class", "material-symbols-outlined");
					    singleNote.appendChild(icon);
					    let buttonNote = document.createElement("a");
					    buttonNote.setAttribute("href", "#");
					    buttonNote.setAttribute("class", "remove");
					    buttonNote.setAttribute("onclick", "remove_note(this)");
					    buttonNote.innerText = "remove";
					    buttonNote.setAttribute("id", "add-note");
					    icon.appendChild(buttonNote);*/
		}else{
			console.error("note-not-successfully-added");
		}
	})
	.catch(error =>{
		console.error('Error:', error);
	})
	
	// After saving a new note, close the form by triggering the click event on the close icon
	closeIcon.click();
	
}
	

function remove_note(noteid, element) {
	let noteElement = element.closest(".note");
	   
    // Get the text content of the note, excluding the 'remove' link
    /*let notetext = '';
    noteElement.childNodes.forEach(node => {
       if (node.nodeType === Node.TEXT_NODE) {
           notetext += node.textContent.trim();
       }
    });

    // Remove any trailing ':'
    notetext = notetext.replace(/:\s*$/, '');*/

    fetch('Delete_note', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            noteid: noteid
        })
    })
    .then(response => response.text())
    .then(responseText => {
        if (responseText === 'delete') {
			console.log("Note deleted");
            noteElement.remove();
        } else {
            console.log("Failed to delete");
        }
    })
    .catch(error => {
        console.error('Error', error);
    })
	

}

// when the 'add new note' button is clicked, add additional 'show' class to .popup-box class.
// 'show' class will reveal the popup form via css
addBox.addEventListener("click", () =>{
  // title field will be focused when form is opened
  titleTag.focus();
  popupBox.classList.add("show");
})

// when the close icon is clicked, remove the 'show' class, which will associated css
closeIcon.addEventListener("click", () =>{
  isUpdate = false; // ensure to reset the isUpdate value
  // clears the input fields after the form is closed
  titleTag.value = "";
  descTag.value = "";
  // When editing a form, the button name changes. This makes sure to revert back on close
  addBtn.innerText = "Add Note";
  // When editing a form, the form title changes. This makes sure to revert back on close
  popupTitle.innerText = "Add a new Note";
  popupBox.classList.remove("show");
})

// show the menu options for edit/deleting notes
function showMenu(item) {
  // get the parent element of the item (e.g since this function is called on a specific instance of <i> element,
  // the parent =  .settings. Add the 'show' class to this instance of .setting which will reveal the menu options via css
  item.parentElement.classList.add("show");

  // Define the event listener function using anonymous expression
  // e represents the event object which we obtain from the "click" event of the document.addEventLister down further in the code
  let clickListener = e => {
    // Checks if the clicked element (e.target) is not an <i> element. (tagName is always uppercase)
    // or if the element is not equal to the 'item' element (e.g. the <i>)
    // if true, remove 'show' class from this instance of .setting on document click
    // E.g. the menu will dissapear when you click anywhere else on the document
    if (e.target.tagName != "I" || e.target != item) {
      item.parentElement.classList.remove("show");
      // Clean up: Removes the event listener after it has been used to prevent multiple listeners from stacking up.
      document.removeEventListener("click", clickListener);
    }
  }
  // listens for click events on the entire document
  // note that Once the showMenu function is executed, the event listener becomes global and will remain active and continue to trigger for events occurring on the document or specified element
  // as long as the event occurs and the listener is not removed.
  // Since 'item' is a parameter of 'showMenu', it is still accessible within the document.addeventlistener function, clickListener, because of JavaScript’s closure mechanism. 
  // Even if showMenu is not called again, the event listener retains access to item due to the closure. This is how the listener is still able to execute the function. 
  document.addEventListener("click", clickListener)
}
