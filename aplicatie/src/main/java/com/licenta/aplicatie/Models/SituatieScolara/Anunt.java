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
@Table(name = "Anunt")
public class Anunt {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "grupa", nullable = false)
    private String grupa;

    @Column(name = "titlu", nullable = false)
    private String titlu;

    @Column(name = "descriere", nullable = false)
    private String descriere;
}
