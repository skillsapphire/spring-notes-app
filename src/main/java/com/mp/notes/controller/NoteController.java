package com.mp.notes.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mp.notes.entities.Note;
import com.mp.notes.exception.ResourceNotFoundException;
import com.mp.notes.repository.NoteRepository;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class NoteController {
	@Autowired
	private NoteRepository noteRepository;
	
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long id){
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note","id",id));
		 //Note note = optNote.get();
		 return note;
	}
	
	@PostMapping("/notes")
	public Note createNote(@RequestBody Note note ){
		return noteRepository.save(note);
	}
	
	@GetMapping("/notes")
	public List<Note> getNotes(){
		return noteRepository.findAll();
	}
	
	@DeleteMapping("notes/{id}")
	public ResponseEntity<?> delNote(@PathVariable(value = "id") Long id){
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note", "id", id));
		noteRepository.delete(note);
		return new ResponseEntity<Note>(note, HttpStatus.NO_CONTENT);
	}
	
	public Integer delAllNote(){
		return null;
	}
	
}
