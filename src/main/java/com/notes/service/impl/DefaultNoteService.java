package com.notes.service.impl;

import com.notes.exception.NoteNotFoundException;
import com.notes.forms.NoteForm;
import com.notes.model.Note;
import com.notes.repository.NoteRepository;
import com.notes.service.NoteService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
@Service
public class DefaultNoteService implements NoteService {
	private static final Logger LOG = LoggerFactory.getLogger(DefaultNoteService.class);

	private final NoteRepository noteRepository;

	@Override
	public List<Note> getAllNotes() {
		LOG.debug("Called getAllNotes");
		return noteRepository.findAll();
	}

	@Override
	public Note getNote(final String id) {
		LOG.debug("Called getNote with id: {}", id);
		return noteRepository.findById(id)
				.orElseThrow(() -> new NoteNotFoundException("No note found with given title"));
	}

	@Override
	public List<Note> getNotesContainsTitle(final String title) throws NoteNotFoundException {
		LOG.debug("Called getNoteContainsTitle with title: {}", title);
		Objects.requireNonNull(title);
		return noteRepository.findByTitleContains(title);
	}

	@Override
	public Note addNote(final NoteForm noteForm) {
		LOG.debug("Called addNote");
		Objects.requireNonNull(noteForm);
		final Note note = new Note(noteForm.getTitle(), noteForm.getContent());
		noteRepository.save(note);
		LOG.info("Note with title: {} and content: {} was successfully saved", note.getTitle(), note.getContent());
		return note;
	}

	@Override
	public Note updateNote(final NoteForm noteForm) {
		LOG.debug("Called updateNote");
		final Note note = noteRepository.findById(String.valueOf(noteForm.getId()))
				.orElseThrow(() -> new NoteNotFoundException("No note found with given title"));
		note.setTitle(noteForm.getTitle());
		note.setContent(noteForm.getContent());
		note.setDate(LocalDateTime.now());
		noteRepository.save(note);
		LOG.info("Note with title: {} and content: {} was successfully saved", note.getTitle(), note.getContent());
		return note;
	}

	@Override
	public void deleteNote(final String id) {
		LOG.debug("Called deleteNote with id: {}", id);
		noteRepository.deleteById(id);
	}
}
