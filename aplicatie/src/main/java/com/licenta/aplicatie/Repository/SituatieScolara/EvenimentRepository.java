package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import com.licenta.aplicatie.Models.SituatieScolara.Eveniment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface EvenimentRepository extends JpaRepository<Eveniment, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM eveniment  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    List<Eveniment> findEvenimenteByDisciplina(int id_disciplina);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO eveniment (id_disciplina, start_date,end_date,titlu,descriere) values (:id_disciplina,:start_date,:end_date,:titlu,:descriere)", nativeQuery = true)
    Optional<Eveniment> addEveniment(int id_disciplina, String start_date,String end_date, String titlu, String descriere);

    @Modifying
    @Query(value = "DELETE FROM eveniment WHERE id_disciplina=:id_disciplina  AND start_date=:start_date AND titlu=:titlu", nativeQuery = true)
    void deleteEveniment(int id_disciplina, String start_date, String titlu);


    @Query(value = "SELECT * FROM eveniment WHERE id_disciplina=:id_disciplina and titlu LIKE '%EXAMEN%'", nativeQuery = true)
    Optional<Eveniment> getEveniment(int id_disciplina);
}
