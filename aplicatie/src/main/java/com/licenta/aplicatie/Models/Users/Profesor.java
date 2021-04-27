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
@Table(name = "Profesor")
public class Profesor {
    @Id
    @Column(name = "id_profesor", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_profesor;

    @Column(name = "nume", nullable = false)
    private String nume;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "telefon", nullable = false)
    private String telefon;

    @Column(name = "functia", nullable = false)
    private String functia;

    public String getFirstName() {
        String[] arr = this.nume.split(" ");
        if (arr.length > 2) {
            return arr[1].concat(" " + arr[2]);
        } else {
            return arr[1];
        }
    }


    public String getLastName() {
        String[] arr = this.nume.split(" ");
        return arr[0];
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "id_profesor=" + id_profesor +
                ", nume='" + nume + '\'' +
                ", email='" + email + '\'' +
                ", telefon='" + telefon + '\'' +
                ", functia='" + functia + '\'' +
                '}';
    }
}
