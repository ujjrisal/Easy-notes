package com.example.notes.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.notes.model.Note;
import com.example.notes.repository.NoteRepository;

@Service
public class NoteServiceImpl implements NoteService {

	private final NoteRepository noteRepository;

	@Autowired
	public NoteServiceImpl(NoteRepository noteRepository) {

		this.noteRepository = noteRepository;
	}

	public List<Note> getAllNotes() {

		return noteRepository.findAll();
	}
	
	public List<Note> searchByTitle(String searchTerm){
		List<Note> allNotes = noteRepository.findAll();
		List<Note> filteredNotes = allNotes.stream()
				.filter(note -> note.getTitle().contains(searchTerm))
				.collect(Collectors.toList());
		return filteredNotes;
	}

	public Note createNote(Note note) {

		return noteRepository.save(note);
	}

	public Note getNoteById(Long id) {
		return noteRepository.findOne(id);
	}

	public void delete(Note note) {
		noteRepository.delete(note);

	}

}
