package com.licenta.aplicatie.Models.SituatieScolara;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Prezenta")
public class Prezenta {
    @Id
    @Column(name="id_disciplina",nullable = false)
    private int id_disciplina;

    @Column(name="id_student",nullable = false)
    private int id_student;

    @Column(name="laborator",nullable = false)
    private int laborator;

    @Column(name="data",nullable = false)
    private Date data;

    @Column(name="prezenta",nullable = false)
    private String prezenta;

}
