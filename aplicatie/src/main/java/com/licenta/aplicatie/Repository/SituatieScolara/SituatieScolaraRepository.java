package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import com.licenta.aplicatie.Models.SituatieScolara.SituatieScolara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface SituatieScolaraRepository extends JpaRepository<SituatieScolara,Integer> {
    @Transactional
    @Query(value = "SELECT medie FROM situatie_scolara  WHERE id_disciplina = :id_disciplina AND id_student = :id_student", nativeQuery = true)
    Optional<Integer> findMedieByDisciplina(int id_disciplina, int id_student);

    @Transactional
    @Query(value = "SELECT medie FROM situatie_scolara  WHERE id_student = :id_student", nativeQuery = true)
    List<Integer> findMedii( int id_student);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM situatie_scolara  WHERE id_student = :id_student", nativeQuery = true)
    List<Integer> findDiscipline( int id_student);

    @Transactional
    @Query(value = "SELECT * FROM situatie_scolara  WHERE id_student = :id_student and id_disciplina=:id_disciplina", nativeQuery = true)
    List<SituatieScolara> findMediiForDisciplines( int id_student,int id_disciplina);
}
