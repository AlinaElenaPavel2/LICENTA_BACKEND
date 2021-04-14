package com.licenta.aplicatie.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "Profesor")
public class Profesor {
    @Id
    @Column(name = "id_profesor", nullable = false)
    int id;

    @Column(name = "nume", nullable = false)
    String nume;

    @Column(name = "email", nullable = false)
    String email;

    @Column(name = "functie", nullable = false)
    String functie;
}