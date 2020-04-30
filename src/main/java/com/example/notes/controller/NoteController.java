package com.example.notes.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.notes.model.Note;
import com.example.notes.service.NoteServiceImpl;

@RestController
@RequestMapping("/notes")

public class NoteController {

	private final NoteServiceImpl noteService;

	@Autowired
	public NoteController(NoteServiceImpl noteService) {
		this.noteService = noteService;
	}

	@GetMapping
	public List<Note> getAllNotes() {
		return noteService.getAllNotes();
	}

	// Create a new Note
	@PostMapping
	public Note createNote(@Valid @RequestBody Note note) {
		return noteService.createNote(note);
	}

	// Get a Single Note

	@GetMapping("/{id}")
	public ResponseEntity<Note> getNoteById(@PathVariable(value = "id") Long noteId) {
		Note note = noteService.getNoteById(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(note);

	}

	@GetMapping("/{searchTerm}")
	public ResponseEntity<List<Note>> searchNotes(@RequestParam(value = "searchTerm") String searchTerm) {
		List<Note> noteList = noteService.searchByTitle(searchTerm);

		return new ResponseEntity<>(noteList, HttpStatus.OK);

	}

	// Update a Note

	@PutMapping("/{id}")
	public ResponseEntity<Note> updateNote(@PathVariable(value = "id") Long noteId,
			@Valid @RequestBody Note noteDetails) {
		Note note = noteService.getNoteById(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}
		note.setTitle(noteDetails.getTitle());
		note.setContent(noteDetails.getContent());

		Note updatedNote = noteService.createNote(note);
		return ResponseEntity.ok(updatedNote);
	}

	// Delete a Note

	@DeleteMapping("/{id}")
	public ResponseEntity<Note> deleteNote(@PathVariable(value = "id") Long noteId) {
		Note note = noteService.getNoteById(noteId);
		if (note == null) {
			return ResponseEntity.notFound().build();
		}

		noteService.delete(note);
		return ResponseEntity.ok().build();
	}

}