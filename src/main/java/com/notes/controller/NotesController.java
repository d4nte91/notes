package com.notes.controller;

import com.notes.forms.NoteForm;
import com.notes.mapper.NoteMapper;
import com.notes.service.NoteService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Controller
@AllArgsConstructor
@Transactional
public class NotesController implements WebMvcConfigurer, ErrorController {
	private static final Logger LOG = LoggerFactory.getLogger(NotesController.class);

	private final NoteService noteService;
	private final NoteMapper noteMapper;


	@Override
	public void addViewControllers(final ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("index");
	}

	@GetMapping
	public String getNotes(final Model model) {
		model.addAttribute("noteForm", new NoteForm());
		model.addAttribute("notes", noteMapper.mapAll(noteService.getAllNotes()));
		return "index";
	}

	@GetMapping("/note/{id}")
	public String getNote(@PathVariable final String id, final Model model) {
		model.addAttribute("noteForm", noteMapper.mapToForm(noteService.getNote(id)));
		return "updateNoteForm";
	}

	@GetMapping("/addNote")
	public String showForm(final NoteForm noteForm) {
		return "addNoteForm";
	}

	@PostMapping("/addNote")
	public String addNote(@ModelAttribute("noteForm") @Valid final NoteForm noteForm, final BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOG.warn("Form validation failed");
			return "addNoteForm";
		}
		noteService.addNote(noteForm);
		return "redirect:/";
	}

	@GetMapping("/delete/{id}")
	public String deleteNote(@PathVariable final String id) {
		noteService.deleteNote(id);
		LOG.info("Note with id: {} was deleted", id);
		return "redirect:/";
	}

	@PostMapping("/note")
	public String updateNote(@ModelAttribute("noteForm") @Valid final NoteForm noteForm, final BindingResult bindingResult, final Model model) {
		if (bindingResult.hasErrors()) {
			LOG.warn("Form validation failed");
			model.addAttribute("noteForm", noteForm);
			return "updateNoteForm";
		}
		noteService.updateNote(noteForm);
		return "redirect:/";
	}

	@PostMapping("/search")
	public String searchNotes(@ModelAttribute("noteForm") final NoteForm noteForm, final Model model) {
		LOG.debug("Search for title: {}", noteForm.getTitle());
		model.addAttribute("notes", noteMapper.mapAll(noteService.getNotesContainsTitle(noteForm.getTitle())));
		return "search";
	}

	@GetMapping("/error")
	public String getErrorPath() {
		LOG.warn("Error occurred");
		return "errorPage";
	}
}
