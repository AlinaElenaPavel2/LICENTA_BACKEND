package com.licenta.aplicatie.Repository.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina,Integer> {
    @Transactional
    @Query(value ="SELECT * FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<Disciplina> findDisciplinaByName(String titlu);

    @Transactional
    @Query(value ="SELECT * FROM disciplina  WHERE id_titular = :id_titular", nativeQuery = true)
    List<Optional<Disciplina>> findDisciplinaByIdTitular(int id_titular);

    @Transactional
    @Query(value ="SELECT id_disciplina FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<Integer> findIdByIdTitlu(String titlu);

    @Transactional
    @Query(value ="SELECT id_disciplina FROM disciplina  WHERE id_titular = :id_titular", nativeQuery = true)
    Optional<Integer> findIdByIdTitular(int id_titular);

    @Transactional
    @Query(value ="SELECT credite FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<Integer> findCrediteByTitlu(String titlu);
}
