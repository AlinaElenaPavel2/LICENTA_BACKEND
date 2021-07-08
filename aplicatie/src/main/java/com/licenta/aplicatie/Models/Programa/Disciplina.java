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
@Table(name = "Disciplina")
public class Disciplina {
    @Id
    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "id_titular", nullable = false)
    private int id_titular;

    @Column(name = "titlu", nullable = false)
    private String nume;

    @Column(name = "credite", nullable = false)
    private int credite;

    @Column(name = "abreviere", nullable = false)
    private String abreviere;

    @Override
    public String toString() {
        return "Disciplina{" +
                "id_disciplina=" + id_disciplina +
                ", id_titular=" + id_titular +
                ", nume='" + nume + '\'' +
                ", credite=" + credite +
                ", abreviere='" + abreviere + '\'' +
                '}';
    }
}
