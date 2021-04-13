package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Integer> {
}
