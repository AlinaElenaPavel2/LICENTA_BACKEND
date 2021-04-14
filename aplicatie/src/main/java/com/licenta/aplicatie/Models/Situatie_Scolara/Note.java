package com.licenta.aplicatie.Models.Situatie_Scolara;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Note_disciplina")
public class Note {
    @Id
    @Column(name="id_student",nullable = false)
    int id_student;

    @Column(name="id_disciplina",nullable = false)
    int id_disciplina;

    @Column(name="laborator",nullable = false)
    int laborator;

    @Column(name="examen",nullable = false)
    int examen;

    @Column(name="partial",nullable = false)
    int partial;
}
