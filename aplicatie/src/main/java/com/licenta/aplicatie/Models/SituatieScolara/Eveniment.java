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
    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "data", nullable = false)
    private Date data;

    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Column(name = "descriere", nullable = false)
    private String descriere;

    @Override
    public String toString() {
        return "Eveniment{" +
                "id_disciplina=" + id_disciplina +
                ", data=" + data +
                ", titlu='" + titlu + '\'' +
                ", descriere='" + descriere + '\'' +
                '}';
    }
}
