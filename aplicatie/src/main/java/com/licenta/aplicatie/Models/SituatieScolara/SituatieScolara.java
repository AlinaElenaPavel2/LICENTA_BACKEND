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
@Table(name="situatie_scolara")
public class SituatieScolara {
    @Id
    @Column(name="id_student",nullable = false)
    private int id_student;

    @Column(name="id_disciplina",nullable = false)
    private int id_disciplina;

    @Column(name="medie",nullable = false)
    private int medie;
}
