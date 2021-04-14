package com.licenta.aplicatie.Repository.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Integer> {
}
