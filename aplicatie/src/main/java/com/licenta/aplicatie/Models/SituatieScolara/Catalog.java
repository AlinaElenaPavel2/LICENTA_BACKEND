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
    private int examen;

    @Column(name="laborator",nullable = false)
    private int laborator;

    @Column(name="partial",nullable = false)
    private int partial;

    @Column(name="proiect",nullable = false)
    private int proiect;
}
