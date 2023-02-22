package com.notes.mapper.impl;

import com.notes.dto.NoteDto;
import com.notes.forms.NoteForm;
import com.notes.mapper.NoteMapper;
import com.notes.model.Note;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NoteMapperImpl implements NoteMapper {

	@Override
	public NoteDto map(final Note note) {
		if (note == null) {
			return null;
		}
		return new NoteDto(note.getId(), note.getTitle(), note.getContent(), note.getDate());
	}

	@Override
	public NoteForm mapToForm(final Note note) {
		if (note == null) {
			return null;
		}
		return new NoteForm(note.getId(), note.getTitle(), note.getContent());
	}

	@Override
	public List<NoteDto> mapAll(final Iterable<Note> notes) {
		final List<NoteDto> notesDto = new ArrayList<>();
		notes.forEach(note -> notesDto.add(map(note)));
		return notesDto;
	}
}
