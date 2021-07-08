package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Models.SituatieScolara.Restanta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestantaRepository extends JpaRepository<Restanta, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM reexaminari  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    List<Restanta> findRestForDiscipline(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM reexaminari  WHERE data = :date", nativeQuery = true)
    List<Restanta> findRestForDate(Date date);

    @Transactional
    @Query(value = "SELECT * FROM reexaminari  WHERE id_student = :id_student", nativeQuery = true)
    List<Restanta> findRestForStudents(int id_student);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM reexaminari  WHERE id_student = :id_student AND tip=:tip", nativeQuery = true)
    Integer findReexamniariForStudents(int id_student,String tip);
}
