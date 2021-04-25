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
@Table(name="User")
public class User {
    @Id
    @Column(name="id_user",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_user;

    @Column(name="id_student",nullable = false)
    private Integer  id_student;

    @Column(name="id_profesor",nullable = false)
    private Integer  id_profesor;

    @Column(name="username",nullable = false)
    private String username;

    @Column(name="password",nullable = false)
    private String password;

    @Column(name = "rol", nullable = false)
    private String role;

    @Lob
    @Column(name = "picture", nullable = false)
    private byte[] picture;
}
