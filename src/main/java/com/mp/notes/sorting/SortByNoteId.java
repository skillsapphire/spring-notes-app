package com.mp.notes.sorting;

import java.util.Comparator;

import com.mp.notes.entities.Note;

public class SortByNoteId implements Comparator<Note>{
	
	@Override
	public int compare(Note o1, Note o2) {
		return (int) (o1.getId()-o2.getId());
	}

}

 