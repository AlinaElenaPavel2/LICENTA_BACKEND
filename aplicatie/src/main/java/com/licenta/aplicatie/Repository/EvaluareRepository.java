package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Evaluare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluareRepository extends JpaRepository<Evaluare,Integer> {
}
