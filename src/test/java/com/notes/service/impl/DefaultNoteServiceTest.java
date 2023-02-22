package com.notes.service.impl;

import com.notes.exception.NoteNotFoundException;
import com.notes.forms.NoteForm;
import com.notes.model.Note;
import com.notes.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Test class for DefaultNoteService
 */
public class DefaultNoteServiceTest {

	private DefaultNoteService service;

	@Mock
	private Note mockNote;
	@Mock
	private NoteForm mockNoteForm;
	@Mock
	private NoteRepository noteRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		service = new DefaultNoteService(noteRepository);
	}

	@Test
	public void testGetNoteNotFound() {
		final String noteId = "123";
		given(noteRepository.findById(noteId)).willThrow(NoteNotFoundException.class);
		assertThrows(NoteNotFoundException.class, () -> service.getNote(noteId));
	}

	@Test
	public void testGetNote() {
		final String noteId = "123";
		given(noteRepository.findById(noteId)).willReturn(Optional.of(mockNote));
		assertEquals(mockNote, service.getNote(noteId));
	}

	@Test
	public void testGetAllNoteNoResults() {
		assertEquals(Collections.emptyList(), service.getAllNotes());
	}

	@Test
	public void testGetAllNote() {
		given(noteRepository.findAll()).willReturn(List.of(mockNote));
		assertEquals(1, service.getAllNotes().size());
	}

	@Test
	public void testGetNotesContainsTitleForNull() {
		assertThrows(NullPointerException.class, () -> service.getNotesContainsTitle(null));
		verify(noteRepository, never()).findByTitleContains(anyString());
	}

	@Test
	public void testGetNotesContainsTitle() {
		final String noteTitle = "title";
		given(noteRepository.findByTitleContains(noteTitle)).willReturn(List.of(mockNote));
		assertEquals(1, service.getNotesContainsTitle(noteTitle).size());
	}

	@Test
	public void testAddNoteNullForm() {
		assertThrows(NullPointerException.class, () -> service.addNote(null));
		verify(noteRepository, never()).save(any(Note.class));
	}

	@Test
	public void testAddNote() {
		service.addNote(mockNoteForm);
		verify(noteRepository, times(1)).save(any(Note.class));
	}

	@Test
	public void testUpdateNoteNoNote() {
		given(noteRepository.findById(anyString())).willReturn(Optional.of(mockNote));
		final Note note = service.updateNote(mockNoteForm);
		verify(noteRepository, times(1)).save(mockNote);
		assertEquals(note, mockNote);
	}

	@Test
	public void testUpdateNote() {
		assertThrows(NoteNotFoundException.class, () -> service.updateNote(mockNoteForm));
	}

	@Test
	public void testDeleteNote() {
		final String noteId = "123";
		service.deleteNote(noteId);
		verify(noteRepository, times(1)).deleteById(noteId);
	}
}
