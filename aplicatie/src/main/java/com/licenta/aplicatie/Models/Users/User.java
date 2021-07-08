package com.licenta.aplicatie.Models.Users;

import com.licenta.aplicatie.Controller.Users.EncryptionAlg;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User {
    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_user;

    @Column(name = "id_student")
    private Integer id_student;

    @Column(name = "id_profesor")
    private Integer id_profesor;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    private String role;

    @Lob
    @Column(name = "picture")
    private String picture;

    @Override
    public String toString() {
        return "User{" +
                "id_user=" + id_user +
                ", id_student=" + id_student +
                ", id_profesor=" + id_profesor +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", picture=" + picture +
                '}';
    }
}
