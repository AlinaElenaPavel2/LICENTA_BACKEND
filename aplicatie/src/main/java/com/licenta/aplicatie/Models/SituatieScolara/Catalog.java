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
@Table(name = "Catalog")
public class Catalog {
    @Id
    @Column(name = "id_student", nullable = false)
    private int id_student;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "examen")
    private Integer examen;

    @Column(name = "laborator")
    private Integer laborator;

    @Column(name = "partial")
    private Integer partial;

    @Column(name = "proiect")
    private Integer proiect;

    @Override
    public String toString() {
        return "Catalog{" +
                "id_student=" + id_student +
                ", id_disciplina=" + id_disciplina +
                ", examen=" + examen +
                ", laborator=" + laborator +
                ", partial=" + partial +
                ", proiect=" + proiect +
                '}';
    }
}
