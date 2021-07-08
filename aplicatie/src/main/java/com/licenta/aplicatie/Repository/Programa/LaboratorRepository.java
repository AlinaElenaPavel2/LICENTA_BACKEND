package com.licenta.aplicatie.Repository.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import com.licenta.aplicatie.Models.Programa.Laborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface LaboratorRepository extends JpaRepository<Laborator,Integer> {
    @Transactional
    @Query(value = "SELECT id_profesor FROM laborator  WHERE id_disciplina = :id_disciplina AND grupa= :grupa", nativeQuery = true)
    Optional<Integer> findLabProfesor(int id_disciplina,String grupa);

    @Transactional
    @Query(value = "SELECT id_profesor FROM laborator  WHERE id_disciplina = :id_disciplina ", nativeQuery = true)
    List<Integer> findLabProfesori(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM laborator  WHERE id_disciplina = :id_disciplina ", nativeQuery = true)
    List<Laborator> findLabProfesoriAndGrupa(int id_disciplina);

    @Transactional
    @Query(value = "SELECT grupa FROM laborator  WHERE id_disciplina = :id_disciplina AND id_profesor=:id_profesor ", nativeQuery = true)
    List<String> findgrupaByDiscipAndProf(int id_disciplina,int id_profesor);

}
