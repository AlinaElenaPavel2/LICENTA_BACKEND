package com.licenta.aplicatie.Models.Studenti;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Student")
public class Student {
    @Id
    @Column(name="id_user",nullable = false)
    int id;

    @Column(name="nume",nullable = false)
    String nume;

    @Column(name="email",nullable = false)
    String email;

    @Column(name="telefon",nullable = false)
    String telefon;

    public String getFirstName()
    {
        String[] array=this.nume.split(" ");
        return array[1];
    }

    public String getLastName()
    {
        String[] array=this.nume.split(" ");
        return array[0];
    }
}
