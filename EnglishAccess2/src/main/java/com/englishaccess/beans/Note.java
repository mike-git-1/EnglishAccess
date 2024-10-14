package com.englishaccess.beans;


import java.sql.Timestamp;
import java.util.UUID;

public class Note {

	private UUID noteID;
	private UUID userID;
	private String title;
	private String noteContent;
	private Timestamp date;
	
	public Note() {
		
	}
	
	public Note(UUID userID, String title, String noteContent) {
		this.noteID = UUID.randomUUID();
		this.userID = userID;
		this.title = title;
		this.noteContent = noteContent;
		this.date = new Timestamp(System.currentTimeMillis());
	}

	public UUID getNoteID() {
		return noteID;
	}
	
	public void setNoteID(UUID noteID) {
		this.noteID = noteID;
	}
	
	public UUID getUserID() {
		return userID;
	}
	
	public void setUserID(UUID userID) {
		this.userID = userID;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getNoteContent() {
		return noteContent;
	}
	
	public void setNoteContent(String noteContent) {
		this.noteContent = noteContent;
	}
	
	public Timestamp getDate() {
		return date;
	}
	
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}
