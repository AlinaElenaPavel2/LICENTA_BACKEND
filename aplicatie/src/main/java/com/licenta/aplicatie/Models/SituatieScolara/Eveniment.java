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
@Table(name = "Eveniment")
public class Eveniment {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "start_date", nullable = false)
    private String start_date;

    @Column(name = "end_date", nullable = false)
    private String end_date;

    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Column(name = "descriere", nullable = false)
    private String descriere;

    @Override
    public String toString() {
        return "Eveniment{" +
                "id=" + id +
                ", id_disciplina=" + id_disciplina +
                ", start_date=" + start_date +
                ", end_date=" + end_date +
                ", titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
