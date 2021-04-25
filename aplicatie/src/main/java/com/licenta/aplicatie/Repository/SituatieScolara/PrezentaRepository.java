package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PrezentaRepository extends JpaRepository<Prezenta,Integer> {
    @Transactional
    @Query(value = "SELECT * FROM prezenta  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    List<Prezenta> findPrezente(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM prezenta  WHERE id_student = :id_student", nativeQuery = true)
    List<Prezenta> findPrezenteByStudent(int id_student);

    @Transactional
    @Query(value = "SELECT * FROM prezenta  WHERE laborator = :laborator", nativeQuery = true)
    List<Prezenta> findPrezenteByLaborator(int laborator);

    @Transactional
    @Query(value = "SELECT * FROM prezenta  WHERE id_disciplina = :id_disciplina AND id_student = :id_student", nativeQuery = true)
    List<Prezenta> findPrezente(int id_disciplina,int id_student );
}
