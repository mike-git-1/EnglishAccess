
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
                newElement.innerText = meanings.partOfSpeech;

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
};



function logout(){
	fetch('Logout', {
		method:'POST', 
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		}
	})
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

function add_note(){
	
	    noteContent = document.getElementById("note-text").value;
		const noteSection = document.getElementById("notes-section")
		
	
			
	
	
	fetch('add_note', {
		method:'POST', 
		headers:{
			'Content-Type':'application/x-www-form-urlencoded'
		},
		body: new URLSearchParams({
			content: noteContent
		})
		
	})
	.then(response =>{
		if (response.ok){
			console.log("note-successfully-added");
			return response.text();
		}
		
		else{
			console.error("note-not-successfully-added");
		}
	})
	
	
	
	.then(text =>{
		const note = document.getElementById("note-text").value;
				    const noteSection = document.getElementById("notes-section");
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
				    icon.appendChild(buttonNote);
	})
	.catch(error =>{
			console.error('Error:', error);
		})
	
}
	

function remove_note(element) {
	let noteElement = element.closest(".single-note");
	   
	   // Get the text content of the note, excluding the 'remove' link
	   let notetext = '';
	   noteElement.childNodes.forEach(node => {
	       if (node.nodeType === Node.TEXT_NODE) {
	           notetext += node.textContent.trim();
	       }
	   });

	   // Remove any trailing ':'
	   notetext = notetext.replace(/:\s*$/, '');

    fetch('Delete_note', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        body: new URLSearchParams({
            NoteText: notetext
        })
    })
    .then(response => response.text())
    .then(responseText => {
        if (responseText === 'delete') {
            noteElement.remove();
        } else {
            console.log("Failed to delete");
        }
    })
    .catch(error => {
        console.error('Error', error);
    });
}

