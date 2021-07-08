package com.licenta.aplicatie.Repository.Programa;

import com.licenta.aplicatie.Models.Programa.Materiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialeRepository extends JpaRepository<Materiale, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM materiale  WHERE id_disciplina = :id_disciplina ", nativeQuery = true)
    List<Materiale> getAllData(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip", nativeQuery = true)
    List<Materiale> getAllData(int id_disciplina,String tip);

    @Transactional
    @Query(value = "SELECT path FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip", nativeQuery = true)
    List<String> getAllPaths(int id_disciplina,String tip);

    @Transactional
    @Query(value = "SELECT descriere FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip", nativeQuery = true)
    List<String> getAllDescriptions(int id_disciplina,String tip);

    @Transactional
    @Query(value = "SELECT descriere FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip and path=:path", nativeQuery = true)
    String getDescriptions(int id_disciplina,String tip,String path);

    @Transactional
    @Query(value = "SELECT titlu FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip", nativeQuery = true)
    List<String> getTitles(int id_disciplina,String tip);

    @Transactional
    @Query(value = "SELECT * FROM materiale  WHERE id_disciplina = :id_disciplina AND tip=:tip", nativeQuery = true)
    List<Materiale> getSelectedData(int id_disciplina,String tip);
}
