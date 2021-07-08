package com.licenta.aplicatie.Repository.Programa;

import com.licenta.aplicatie.Models.Programa.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplinaRepository extends JpaRepository<Disciplina, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<Disciplina> findDisciplinaByName(String titlu);

    @Transactional
    @Query(value = "SELECT * FROM disciplina  WHERE id_titular = :id_titular", nativeQuery = true)
    List<Disciplina> findDisciplinaByIdTitular(int id_titular);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<List<Integer>> findIdByIdTitlu(String titlu);


    @Transactional
    @Query(value = "SELECT id_disciplina FROM disciplina  WHERE id_titular = :id_titular", nativeQuery = true)
    Optional<Integer> findIdByIdTitular(int id_titular);

    @Transactional
    @Query(value = "SELECT credite FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<Integer> findCrediteByTitlu(String titlu);

    @Transactional
    @Query(value = "SELECT abreviere FROM disciplina  WHERE titlu = :titlu", nativeQuery = true)
    Optional<String> findAbreviereByTitlu(String titlu);

    @Transactional
    @Query(value = "SELECT titlu FROM disciplina  WHERE abreviere = :abreviere", nativeQuery = true)
    Optional<String> findTitluByAbreviere(String abreviere);

    @Transactional
    @Query(value = "SELECT * FROM disciplina  WHERE abreviere = :abreviere", nativeQuery = true)
    List<Optional<Disciplina>> findDisciplinaByAbreviere(String abreviere);

    @Transactional
    @Query(value = "SELECT * FROM disciplina  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    Disciplina findDisciplina(int id_disciplina);
}
