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
@Table(name="Disciplina")
public class Disciplina {
    @Id
    @Column(name="id_disciplina",nullable = false)
    int id;

    @Column(name="id_titular",nullable = false)
    int id_titular;

    @Column(name="titlu",nullable = false)
    String titlu;

    @Column(name="credite",nullable = false)
    int credite;
}
