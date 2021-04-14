package com.licenta.aplicatie.Repository.Student;

import com.licenta.aplicatie.Models.Studenti.DetaliiStudent;
import com.licenta.aplicatie.Models.Studenti.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface DetaliiStudentRepository extends JpaRepository<DetaliiStudent,Integer> {

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE an = :an", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentByAn(int an);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE grupa = :grupa", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentByGrupa(String grupa);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE specializare = :specializare", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentsBySpecializare(String specializare);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE grupa = :grupa AND specializare = :specializare", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentsByGrupaAndSpecializare(String grupa,String specializare);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE an = :an AND grupa = :grupa", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentsByYearAndGrupa(int an, String grupa);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE an = :an AND specializare = :specializare", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentsByYearAndSpecializare(int an, String specializare);

    @Transactional
    @Query(value ="SELECT * FROM detalii_student  WHERE an = :an AND grupa = :grupa AND specializare = :specializare", nativeQuery = true)
    List<Optional<DetaliiStudent>> findStudentsByAllCriterias(int an,String grupa, String specializare);

}
