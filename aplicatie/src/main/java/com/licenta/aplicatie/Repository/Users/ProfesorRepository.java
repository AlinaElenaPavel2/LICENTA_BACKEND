package com.licenta.aplicatie.Repository.Users;

import com.licenta.aplicatie.Models.Users.Profesor;
import com.licenta.aplicatie.Models.Users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
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

    @Transactional
    @Query(value ="SELECT id_profesor FROM Profesor  WHERE email = :email", nativeQuery = true)
    Optional<Integer> findProfIdByEmail(String email);
}
