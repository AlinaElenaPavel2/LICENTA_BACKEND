package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Catalog;
import com.licenta.aplicatie.Models.SituatieScolara.Evaluare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CatalogRepository extends JpaRepository<Catalog,Integer> {
    @Transactional
    @Query(value = "SELECT * FROM catalog  WHERE id_disciplina = :id_disciplina AND id_student = :id_student", nativeQuery = true)
    Optional<Catalog> findMark(int id_disciplina,int id_student);
}
