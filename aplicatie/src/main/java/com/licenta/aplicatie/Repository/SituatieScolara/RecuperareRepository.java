package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import com.licenta.aplicatie.Models.SituatieScolara.Recuperare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository

public interface RecuperareRepository extends JpaRepository<Recuperare, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM recuperare  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    List<Recuperare> getCereri(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM recuperare  WHERE id_disciplina = :id_disciplina AND grupa=:grupa", nativeQuery = true)
    List<Recuperare> getCereriByGrupa(int id_disciplina, String grupa);

    @Transactional
    @Query(value = "SELECT * FROM recuperare  WHERE id_disciplina = :id_disciplina AND grupa=:grupa And accept=:accept and data=:data", nativeQuery = true)
    List<Recuperare> cereriAcceptate(int id_disciplina, String grupa, String data, String accept);
}
