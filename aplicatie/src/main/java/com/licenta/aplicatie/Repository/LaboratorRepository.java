package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Laborator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorRepository extends JpaRepository<Laborator,Integer> {
}
