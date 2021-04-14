package com.licenta.aplicatie.Repository.Student;

import com.licenta.aplicatie.Models.Studenti.Student;
import com.licenta.aplicatie.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface StudentRepository extends JpaRepository<Student,Integer> {
    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE nume = :name", nativeQuery = true)
    Optional<Student> findStudentByName(String name);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE email = :email", nativeQuery = true)
    Optional<Student> findStudentByEmail(String email);

    @Transactional
    @Query(value ="SELECT id_student FROM Student  WHERE nume = :name", nativeQuery = true)
    Optional<Integer> findStudentIdByName(String name);

    @Transactional
    @Query(value ="SELECT id_student FROM Student  WHERE email = :email", nativeQuery = true)
    Optional<Integer> findStudentIdByEmail(String email);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE id_student = :id", nativeQuery = true)
    Optional<Student> findStudentById(int id);

    @Transactional
    @Query(value ="SELECT * FROM Student", nativeQuery = true)
    List<Student> findStudents();
}
