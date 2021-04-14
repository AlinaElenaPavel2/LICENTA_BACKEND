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
@Table(name="Evaluare")
public class Evaluare {
    @Id
    @Column(name="id_disciplina",nullable = false)
    int id_disciplina;

    @Column(name="pondere_lab",nullable = false)
    int pondere_lab;

    @Column(name="pondere_examen",nullable = false)
    int pondere_examen;

    @Column(name="pondere_partial",nullable = false)
    int pondere_partial;
}
