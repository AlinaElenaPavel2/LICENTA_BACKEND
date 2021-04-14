package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Profesor;
import com.licenta.aplicatie.Models.Studenti.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface ProfesorRepository  extends JpaRepository<Profesor,Integer> {
    @Transactional
    @Query(value ="SELECT * FROM Profesor  WHERE nume = :name", nativeQuery = true)
    Optional<Profesor> findProfesorByName(String name);

    @Transactional
    @Query(value ="SELECT * FROM Profesor  WHERE email = :email", nativeQuery = true)
    Optional<Profesor> findProfesorByEmail(String email);

    @Transactional
    @Query(value ="SELECT * FROM Profesor  WHERE functia = :functia", nativeQuery = true)
    Optional<Profesor> findProfesorByFunctia(String functia);

    @Transactional
    @Query(value ="SELECT id_profesor FROM Profesor  WHERE nume = :name", nativeQuery = true)
    Optional<Integer> findProfesorIdByName(String name);

}
