package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.DisciplineAni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DisciplineAniRepository extends JpaRepository<DisciplineAni,Integer> {
}
