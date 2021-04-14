package com.licenta.aplicatie.Repository.Situatie_Scolara;

import com.licenta.aplicatie.Models.Situatie_Scolara.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository  extends JpaRepository<Note,Integer> {
}
