package com.notes.service;

import com.notes.exception.NoteNotFoundException;
import com.notes.forms.NoteForm;
import com.notes.model.Note;

import java.util.List;

public interface NoteService {

	/**
	 * Method that returns all notes
	 *
	 * @return set of notes
	 */
	List<Note> getAllNotes();

	/**
	 * Method that returns note for id
	 *
	 * @param id parameter of a note
	 * @return note
	 */
	Note getNote(String id);

	/**
	 * Method that returns a notes which contains <code>title</code>
	 *
	 * @param title parameter of a note
	 * @return notes that contains given title
	 */
	List<Note> getNotesContainsTitle(String title) throws NoteNotFoundException;

	/**
	 * Method that saves a note to db
	 *
	 * @param noteForm form with note related attributes
	 * @return saved note in db
	 */
	Note addNote(NoteForm noteForm);

	/**
	 * Method that updates a note
	 *
	 * @param noteForm attributes that will be set for note
	 * @return updated note
	 */
	Note updateNote(NoteForm noteForm);

	/**
	 * Method that deletes note for given <code>id</code>
	 *
	 * @param id    id of note that will be deleted
	 */
	void deleteNote(String id);
}
