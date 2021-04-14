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
@Table(name="Discipline_ani")
public class DisciplineAni {
    @Id
    @Column(name="id_disciplina",nullable = false)
    int id_disciplina;

    @Column(name="semestru",nullable = false)
    int semestru;

    @Column(name="specializare",nullable = false)
    String specializare;

    @Column(name="an",nullable = false)
    int an;
}
