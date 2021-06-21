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
@Table(name = "recuperare")
public class Recuperare {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "id_student", nullable = false)
    private int id_student;

    @Column(name = "laborator")
    private int laborator;

    @Column(name = "grupa")
    private int grupa;

    @Column(name = "data")
    private String data;

    @Column(name = "accept")
    private String accept;

    @Override
    public String toString() {
        return "Recuperare{" +
                "id=" + id +
                ", id_disciplina=" + id_disciplina +
                ", id_student=" + id_student +
                ", laborator=" + laborator +
                ", data='" + data + '\'' +
                ", accept='" + accept + '\'' +
                '}';
    }
}
