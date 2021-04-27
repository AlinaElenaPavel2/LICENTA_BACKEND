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
@Table(name = "Evaluare")
public class Evaluare {
    @Id
    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "pondere_lab", nullable = false)
    private Integer pondere_lab;

    @Column(name = "pondere_examen", nullable = false)
    private Integer pondere_examen;

    @Column(name = "pondere_partial", nullable = false)
    private Integer pondere_partial;

    @Column(name = "pondere_proiect", nullable = false)
    private Integer pondere_proiect;

    @Override
    public String toString() {
        return "Evaluare{" +
                "id_disciplina=" + id_disciplina +
                ", pondere_lab=" + pondere_lab +
                ", pondere_examen=" + pondere_examen +
                ", pondere_partial=" + pondere_partial +
                ", pondere_proiect=" + pondere_proiect +
                '}';
    }
}
