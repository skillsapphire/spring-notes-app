package com.mp.notes.controller;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mp.notes.NotesApplication;
import com.mp.notes.entities.Note;
import com.mp.notes.exception.ResourceNotFoundException;
import com.mp.notes.repository.NoteRepository;
import com.mp.notes.sorting.SortByCreatedOn;
import com.mp.notes.sorting.SortByNoteId;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class NoteController {
	
	private static final Logger logger = LogManager.getLogger(NoteController.class);
	
	@Autowired
	private NoteRepository noteRepository;
	
	@GetMapping("/notes/{id}")
	public Note getNoteById(@PathVariable(value = "id") Long id){
		logger.debug("calling /notes/id with id = "+id);
		Note note = noteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Note","id",id));
		 //Note note = optNote.get();
		 return note;
	}
	
	@PostMapping("/notes")
	public Note createNote(@RequestBody Note note ){
		return noteRepository.save(note);
	}
	
	@GetMapping("/notes")
	public List<Note> getNotes(@RequestParam (name = "field", required=true, defaultValue="id") String field, 
			@RequestParam(name = "type", required=true, defaultValue="asc" )  String type){
		
		List<Note> allNotes = noteRepository.findAll();
		if(field.equals("id")){
			if(type.equals("asc")){
				Collections.sort(allNotes,new SortByNoteId());
			}else if(type.equals("desc")){
				allNotes.sort(Collections.reverseOrder(new SortByNoteId()));
			}
		}else if(field.equals("creationDate")){
			if(type.equals("asc")){
				Collections.sort(allNotes,new SortByCreatedOn());
			}else if(type.equals("desc")){
				allNotes.sort(Collections.reverseOrder(new SortByCreatedOn()));
			}

		}
		 
		return allNotes;
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
