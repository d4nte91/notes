package com.notes.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Note model
 */
@Entity(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String title;
	private String content;
	private LocalDateTime date;

	public Note(final String title, final String content) {
		this.title = title;
		this.content = content;
		this.date = LocalDateTime.now();
	}
}
