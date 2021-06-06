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
    @Query(value = "SELECT data FROM eveniment  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    Optional<Eveniment> findDatesByDisciplina(int id_disciplina);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO eveniment (id_disciplina, data,titilu,descriere) values (:id_disciplina,:data,:titlu,:descriere)", nativeQuery = true)
    Optional<Eveniment> addEveniment(int id_disciplina, Date data, String titlu, String descriere);

    @Transactional
    @Query(value = "DELETE FROM eveniment WHERE id_disciplina=:id_disciplina  AND data=:data AND titlu=:titlu AND descriere=:descriere", nativeQuery = true)
    void deleteEveniment(int id_disciplina, Date data, String titlu, String descriere);

    @Transactional
    @Query(value = "SELECT * FROM eveniment WHERE id_disciplina=:id_disciplina and titlu LIKE '%EXAMEN%'", nativeQuery = true)
    Optional<Eveniment> getEveniment(int id_disciplina);
}
