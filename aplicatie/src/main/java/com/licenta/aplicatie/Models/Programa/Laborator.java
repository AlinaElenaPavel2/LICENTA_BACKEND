package com.licenta.aplicatie.Models.Programa;

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
@Table(name="Laborator")
public class Laborator {
    @Id
    @Column(name="id_disciplina",nullable = false)
    private int id_disciplina;

    @Column(name="id_profesor",nullable = false)
    private int id_profesor;

    @Column(name="grupa",nullable = false)
    private String grupa;

}
