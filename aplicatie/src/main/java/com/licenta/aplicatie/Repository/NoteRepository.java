package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository  extends JpaRepository<Note,Integer> {
}
