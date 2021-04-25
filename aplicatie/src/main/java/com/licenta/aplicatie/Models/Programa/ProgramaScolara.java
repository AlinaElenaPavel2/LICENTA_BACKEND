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
@Table(name="programa_scolara")
public class ProgramaScolara {
    @Id
    @Column(name="id_disciplina",nullable = false)
    private int id_disciplina;

    @Column(name="program_studii",nullable = false)
    private String programStudii;

    @Column(name="specializare",nullable = false)
    private String specializare;

    @Column(name="an",nullable = false)
    private int an;

    @Column(name="semestru",nullable = false)
    private int semestru;
}
