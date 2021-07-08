package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface EvaluareRepository extends JpaRepository<Evaluare,Integer>
{
    @Transactional
    @Query(value = "SELECT * FROM evaluare  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    Optional<Evaluare> findProcents(int id_disciplina);
}
