package com.licenta.aplicatie.Repository.SituatieScolara;

import com.licenta.aplicatie.Models.SituatieScolara.Prezenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PrezentaRepository extends JpaRepository<Prezenta,Integer> {
    @Transactional
    @Query(value = "SELECT * FROM prezentaSecond  WHERE id_disciplina = :id_disciplina", nativeQuery = true)
    List<Prezenta> findPrezente(int id_disciplina);

    @Transactional
    @Query(value = "SELECT * FROM prezentaSecond  WHERE id_student = :id_student", nativeQuery = true)
    List<Prezenta> findPrezenteByStudent(int id_student);

    @Transactional
    @Query(value = "SELECT * FROM prezentaSecond  WHERE laborator = :laborator", nativeQuery = true)
    List<Prezenta> findPrezenteByLaborator(int laborator);


    @Query(value = "SELECT * FROM prezentaSecond  WHERE id_disciplina = :id_disciplina AND id_student = :id_student", nativeQuery = true)
    List<Prezenta> findPrezente(int id_disciplina,int id_student );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO prezentaSecond (id_disciplina,id_student,laborator,data,prezenta)  VALUES (:id_disciplina,:id_student,:laborator,:data,:prezenta)", nativeQuery = true)
    void adaugarePrezenta(int id_disciplina,int id_student,int laborator,String data,String prezenta );


}
