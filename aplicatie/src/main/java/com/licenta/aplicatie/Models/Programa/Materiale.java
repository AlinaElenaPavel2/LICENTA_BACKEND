package com.licenta.aplicatie.Models.Programa;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Materiale")
public class Materiale {
    @Id
    @Column(name = "id_disciplina", nullable = false)
    private int id_disciplina;

    @Column(name = "path", nullable = false)
    private String path;

    @Column(name = "descriere", nullable = false)
    private String descriere;

    @Column(name = "tip", nullable = false)
    private String tip;
}
