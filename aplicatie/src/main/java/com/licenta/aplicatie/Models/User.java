package com.licenta.aplicatie.Models;

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
@Data
@Table(name="User")
public class User {
    @Id
    @Column(name="id_user",nullable = false)
    int id;

    @Column(name="username",nullable = false)
    String username;

    @Column(name="password",nullable = false)
    String parola;

    @Column(name="rol",nullable = false)
    String rol;

}
