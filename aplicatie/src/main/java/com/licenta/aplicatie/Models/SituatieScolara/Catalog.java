package com.licenta.aplicatie.Models.SituatieScolara;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="Catalog")
public class Catalog {
    @Id
    @Column(name="id_student",nullable = false)
    private int id_student;

    @Column(name="id_disciplina",nullable = false)
    private int id_disciplina;

    @Column(name="examen",nullable = false)
    private Integer examen;

    @Column(name="laborator",nullable = false)
    private Integer laborator;

    @Column(name="partial",nullable = false)
    private Integer partial;

    @Column(name="proiect",nullable = false)
    private Integer proiect;
}
