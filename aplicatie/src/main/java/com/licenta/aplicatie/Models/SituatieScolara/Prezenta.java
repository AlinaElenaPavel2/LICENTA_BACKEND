package com.licenta.aplicatie.Models.SituatieScolara;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "prezentaSecond")
public class Prezenta {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "id_student", nullable = false)
    private int id_student;

    @Column(name = "laborator")
    private int laborator;

    @Column(name = "data")
    private String data;

    @Column(name = "prezenta")
    private String prezenta;

    @Override
    public String toString() {
        return "Prezenta{" +
                "id_disciplina=" + id_disciplina +
                ", id_student=" + id_student +
                ", laborator=" + laborator +
                ", data=" + data +
                ", prezenta='" + prezenta + '\'' +
                '}';
    }
}
