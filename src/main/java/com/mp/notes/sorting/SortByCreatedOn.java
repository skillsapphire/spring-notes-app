package com.mp.notes.sorting;

import java.util.Comparator;

import com.mp.notes.entities.Note;

public class SortByCreatedOn implements Comparator<Note>{

	@Override
	public int compare(Note o1, Note o2) {
		return o1.getCreatedAt().compareTo(o2.getCreatedAt());
	}
	
}
