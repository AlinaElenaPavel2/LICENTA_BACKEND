package com.licenta.aplicatie.Repository.Discipline;

import com.licenta.aplicatie.Models.Discipline.DisciplineAni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineAniRepository extends JpaRepository<DisciplineAni,Integer> {
}
