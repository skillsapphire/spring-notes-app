package com.mp.notes.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.mp.notes.entities.Note;
import com.mp.notes.repository.NoteRepository;

@RunWith(MockitoJUnitRunner.class)
public class NotesControllerTest {

	@InjectMocks
	NoteController noteController = new NoteController();

	@Mock
	private NoteRepository noteRepository;
	
	Optional<Note> optNote = null;
	
	private Note saveNote = null;
	
	List<Note> notesList = new ArrayList<Note>();
	Date d = new Date();

	@Before
	public void setUp() {
		
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		
		//System.out.println(dt);
		
		Note note = new Note();
		note.setId(1L);
		note.setContent("Content");
		note.setTitle("title");
		note.setCreatedAt(new Date());
		note.setLastModifiedAt(new Date());
		optNote = Optional.of(note);
		
		saveNote = new Note();
		saveNote.setId(new Long(4));
		saveNote.setContent("content4");
		saveNote.setTitle("titlesave4");
		
		c.add(Calendar.DATE, 4);
		dt = c.getTime();
		
		saveNote.setCreatedAt(dt);
		saveNote.setLastModifiedAt(dt);
		notesList.add(saveNote);
		
		saveNote = new Note();
		saveNote.setId(new Long(1));
		saveNote.setContent("content1");
		saveNote.setTitle("titlesave1");
		c.add(Calendar.DATE, 1);
		dt = c.getTime();
		
	
		
		saveNote.setCreatedAt(dt);
		saveNote.setLastModifiedAt(dt);
		notesList.add(saveNote);
		
		saveNote = new Note();
		saveNote.setId(new Long(3));
		saveNote.setContent("content3");
		saveNote.setTitle("titlesave3");
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		d=dt;
		
		saveNote.setCreatedAt(dt);
		saveNote.setLastModifiedAt(dt);
		notesList.add(saveNote);
		
	}

	//@Test
	public void testGetNoteById() {
		
		when(noteRepository.findById(1L)).thenReturn(optNote);
		Note newNote = noteController.getNoteById(1L);
		assertEquals("Matched",optNote.get().getContent(), newNote.getContent());
	}

	//@Test
	public void TestcreateNote(){
		when(noteRepository.save(saveNote)).thenReturn(saveNote);
		Note savedNote = noteController.createNote(saveNote);
		assertEquals("Saved", saveNote, savedNote);
	}
	
	//@Test
	public void testGetNotes_id_asc(){
		when(noteRepository.findAll()).thenReturn(notesList);
		notesList =  noteController.getNotes("id", "asc");
		assertEquals(notesList.get(0).getId(), new Long(1));
	}
	//@Test
	public void testGetNotes_id_desc(){	
		when(noteRepository.findAll()).thenReturn(notesList);
		notesList =  noteController.getNotes("id", "desc");
		assertEquals(notesList.get(0).getId(), new Long(4));
		
	}
	
	@Test
	public void testCreationDateAsc(){
		when(noteRepository.findAll()).thenReturn(notesList);
		notesList = noteController.getNotes("creationDate", "desc");
		assertEquals(notesList.get(0).getCreatedAt(), d);
	}
}
