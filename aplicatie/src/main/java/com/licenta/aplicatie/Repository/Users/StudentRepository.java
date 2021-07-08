package com.licenta.aplicatie.Repository.Users;

import com.licenta.aplicatie.Models.Users.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
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



    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE an = :an", nativeQuery = true)
    List<Optional<Student>> findStudentByAn(int an);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE grupa = :grupa", nativeQuery = true)
    List<Optional<Student>> findStudentByGrupa(String grupa);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE specializare = :specializare", nativeQuery = true)
    List<Optional<Student>> findStudentsBySpecializare(String specializare);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE program_studiu = :program_studiu", nativeQuery = true)
    List<Optional<Student>> findStudentsByProgramStudii(String program_studiu);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE grupa = :grupa AND specializare = :specializare", nativeQuery = true)
    List<Optional<Student>> findStudentsByGrupaAndSpecializare(String grupa,String specializare);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE an = :an AND grupa = :grupa", nativeQuery = true)
    List<Optional<Student>> findStudentsByYearAndGrupa(int an, String grupa);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE an = :an AND specializare = :specializare", nativeQuery = true)
    List<Optional<Student>> findStudentsByYearAndSpecializare(int an, String specializare);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE an = :an AND grupa = :grupa AND specializare = :specializare", nativeQuery = true)
    List<Optional<Student>> findStudentsByAllCriterias(int an,String grupa, String specializare);

    @Transactional
    @Query(value ="SELECT * FROM Student  WHERE an = :an AND grupa = :grupa AND specializare = :specializare AND program_studiu = :program_studiu", nativeQuery = true)
    List<Optional<Student>> findStudentsByAllCriterias(int an,String grupa, String specializare,String program_studiu);
}
