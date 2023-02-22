package com.notes.mapper;

import com.notes.dto.NoteDto;
import com.notes.forms.NoteForm;
import com.notes.model.Note;

import java.util.List;

public interface NoteMapper {

	NoteDto map(Note note);
	NoteForm mapToForm(Note note);
	List<NoteDto> mapAll(Iterable<Note> notes);
}
