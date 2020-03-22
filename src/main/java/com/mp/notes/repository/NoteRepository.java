package com.mp.notes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mp.notes.entities.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note,Long> {

}
