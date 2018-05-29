package com.example.easynotes.service;

import java.util.List;

import com.example.easynotes.model.Note;

public interface NoteService {

	public List<Note> getAllNotes();

	public Note createNote(Note note);

	public Note getNoteById(Long id);

	public void delete(Note note);

	public List<Note> searchByTitle(String searchTerm);

}
