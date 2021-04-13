package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesorRepository  extends JpaRepository<Profesor,Integer> {
}
