package com.licenta.aplicatie.Models.Discipline;

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
@Table(name="Laborator")
public class Laborator {
    @Id
    @Column(name="id_disciplina",nullable = false)
    int id_disciplina;

    @Column(name="id_profesor",nullable = false)
    int id_profesor;

    @Column(name="grupa",nullable = false)
    String grupa;
}
