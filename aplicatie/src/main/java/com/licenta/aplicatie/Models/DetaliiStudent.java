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
@Table(name="Detalii_student")
public class DetaliiStudent {
    @Id
    @Column(name="id_student",nullable = false)
    int id_student;

    @Column(name="an",nullable = false)
    int an;

    @Column(name="grupa",nullable = false)
    String grupa;

    @Column(name="specializare",nullable = false)
    String specializare;
}
