package com.licenta.aplicatie.Repository.Discipline;

import com.licenta.aplicatie.Models.Discipline.Disciplina;
import com.licenta.aplicatie.Models.Discipline.DisciplineAni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface DisciplineAniRepository extends JpaRepository<DisciplineAni,Integer> {
    @Transactional
    @Query(value ="SELECT id_disciplina FROM Discipline_ani  WHERE an = :an", nativeQuery = true)
    List<Optional<Integer>> findDisciplinebyYear(int an);

    @Transactional
    @Query(value ="SELECT id_disciplina FROM Discipline_ani  WHERE an = :an AND semestru = :semestru", nativeQuery = true)
    List<Optional<Integer>> findDisciplinaBySemester(int an,int semestru);

    @Transactional
    @Query(value ="SELECT id_disciplina FROM Discipline_ani  WHERE an = :an AND semestru = :semestru AND specializare = :specializare", nativeQuery = true)
    List<Optional<Integer>> findDisciplinaByAll(int an,int semestru,String specializare);

}
