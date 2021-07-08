package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Anunt;
import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface AnuntRepository extends JpaRepository<Anunt,Integer> {
    @Transactional
    @Query(value = "SELECT * FROM anunt  WHERE id_disciplina = :id_disciplina AND grupa = :grupa", nativeQuery = true)
    List<Anunt> getAnunturi(int id_disciplina, String grupa);


}
