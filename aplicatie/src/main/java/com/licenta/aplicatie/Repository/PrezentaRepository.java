package com.licenta.aplicatie.Repository;

import com.licenta.aplicatie.Models.Prezenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrezentaRepository  extends JpaRepository<Prezenta,Integer> {
}
