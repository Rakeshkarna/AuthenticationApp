package com.example.AuthApp.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Users {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "username", nullable = false , columnDefinition = "text")
    private String username;

    @Column(name = "email" , nullable = false , columnDefinition = "text")
    private String email;

    @Column(name ="password" ,columnDefinition = "text")
    private String password;
}
