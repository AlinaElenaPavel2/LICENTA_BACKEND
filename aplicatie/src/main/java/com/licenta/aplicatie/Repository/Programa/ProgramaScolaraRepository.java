package com.licenta.aplicatie.Repository.Programa;

import com.licenta.aplicatie.Models.Programa.ProgramaScolara;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProgramaScolaraRepository  extends JpaRepository<ProgramaScolara,Integer> {
    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii", nativeQuery = true)
    List<Integer> findDisciplinesByProgramStudii(String programStudii);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii AND specializare = :specializare", nativeQuery = true)
    List<Integer> findDisciplinesBySpecializare(String programStudii,String specializare);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii AND an = :an", nativeQuery = true)
    List<Integer> findDisciplinesByAn(String programStudii,int an);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii AND semestru = :semestru", nativeQuery = true)
    List<Integer> findDisciplinesBySemestru(String programStudii,String semestru);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii AND  specializare = :specializare AND an = :an ", nativeQuery = true)
    List<Integer> findDisciplinesBySpecializareAndAn(String programStudii,String specializare,int an);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE program_studii = :programStudii AND  an = :an AND semestru = :semestru ", nativeQuery = true)
    List<Integer> findDisciplinesByAnAndSemestru(String programStudii,int an,int semestru);

    @Transactional
    @Query(value = "SELECT id_disciplina FROM programa_scolara  WHERE  program_studii = :programStudii AND specializare = :specializare AND an = :an AND semestru = :semestru ", nativeQuery = true)
    List<Integer>  findDisciplines(String programStudii,String specializare,int an,int semestru);

    @Transactional
    @Query(value = "SELECT * FROM programa_scolara  WHERE  id_disciplina = :id_disciplina", nativeQuery = true)
    Optional<ProgramaScolara>  findDisciplineDetails(int id_disciplina);
}
