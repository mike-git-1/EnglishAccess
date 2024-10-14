package com.englishaccess.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;
import javax.sql.DataSource;
import com.englishaccess.beans.Note;


public class NotesDao {
	
	private DataSource dataSource; // we set this value to private the object
	
	private static final String selectAllNotes = "SELECT * FROM user_notes ORDER BY date_created DESC";
	private static final String insertNote = "INSERT INTO user_notes(NoteID, UserID, Note_Title, Note_Content, date_created) VALUES (?, ?, ?, ?, ?)";
	private static final String deleteNote = "DELETE FROM user_notes WHERE NoteID = ?";
	
	public NotesDao(DataSource dataSource) {
		this.dataSource = dataSource;

	}
	
	
	
	public void insertNote(Note note) throws SQLException{
		
		try (
				
			Connection connection = dataSource.getConnection();
			PreparedStatement statement2 = connection.prepareStatement(insertNote);) {
			
			statement2.setString(1, note.getNoteID().toString());
			statement2.setString(2, note.getUserID().toString());
			statement2.setString(3, note.getTitle());
			statement2.setString(4, note.getNoteContent());
			statement2.setTimestamp(5, note.getDate());
			
			statement2.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}		
	
    public Map<UUID, Note> readAllNotes() {
        Note note = null;
        // map = a collection that holds key-value pairs. Each key is unique, and is used to retrieve the value.
        // this map holds a collection of log objects with their uuids as keys
        Map<UUID, Note> notes = new LinkedHashMap<UUID, Note>();
        
		try (
				
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(selectAllNotes);) {
  
            // execute query, get resultset and return User info
            ResultSet set = statement.executeQuery();
            while (set.next()) {
            	// get note info form db and store them in note object
                note = new Note();
                note.setNoteID(UUID.fromString(set.getString("noteid")));
                note.setUserID(UUID.fromString(set.getString("userid")));
                note.setTitle(set.getString("note_title"));
                note.setNoteContent(set.getString("note_content"));
                note.setDate(set.getTimestamp("date_created"));
         
                // store log objects inside a map (collection) with their respective uuids as the key
                notes.put(note.getNoteID(), note);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
		
		return notes;
    }
     
	
	public int deleteNote(String noteid) throws SQLException{
		int rowsAffected = 0;
		try (
				
			Connection connection = dataSource.getConnection();
			PreparedStatement statement = connection.prepareStatement(deleteNote);) {
			
			statement.setString(1, noteid);
			rowsAffected = statement.executeUpdate();
  
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		return rowsAffected;
	}
	
		

}
