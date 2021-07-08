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
@Table(name = "situatie_scolara")
public class SituatieScolara {
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_student", nullable = false)
    private int id_student;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "medie", nullable = false)
    private int medie;

    @Override
    public String toString() {
        return "SituatieScolara{" +
                "id_student=" + id_student +
                ", id_disciplina=" + id_disciplina +
                ", medie=" + medie +
                '}';
    }
}
