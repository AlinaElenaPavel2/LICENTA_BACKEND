package com.licenta.aplicatie.Models.Users;

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
@Table(name = "Student")
public class Student {
    @Id
    @Column(name = "id_student", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_student;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefon", nullable = false)
    private String telefon;

    @Column(name = "an", nullable = false)
    private int an;

    @Column(name = "specializare", nullable = false)
    private String specializare;

    @Column(name = "grupa", nullable = false)
    private String grupa;

    @Column(name = "program_studiu", nullable = false)
    private String program_studiu;

    public String getFirstName() {
        String[] array = this.nume.split(" ");
        if (array.length > 2)
            return array[1].concat(" " + array[2]);
        else
            return array[1];
    }

    public String getLastName() {
        String[] array = this.nume.split(" ");
        return array[0];
    }

    @Override
    public String toString() {
        return "Student{" +
                "id_student=" + id_student +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", an=" + an +
                ", specializare='" + specializare + '\'' +
                ", grupa='" + grupa + '\'' +
                ", program_studiu='" + program_studiu + '\'' +
                '}';
    }
}
