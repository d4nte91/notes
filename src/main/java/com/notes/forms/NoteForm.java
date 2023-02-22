package com.notes.forms;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteForm {
	private Long id;
	@Pattern(regexp = "^[a-zA-Z]*", message = "Title can contain only letters")
	@NotEmpty(message = "Content cannot be blank")
	private String title;
	@Pattern(regexp = "^[a-zA-Z0-9\s]*", message = "Title can contain only letters, numbers and spaces")
	@NotEmpty(message = "Content cannot be blank")
	private String content;
}
