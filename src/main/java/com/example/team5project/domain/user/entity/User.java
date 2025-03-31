package com.example.team5project.domain.user.entity;

import com.example.team5project.domain.user.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String email;

    @Column
    private String password;

    private UserRole userRole;

    public User(String email, String name, String encodedPassword, UserRole userRole) {
        this.email = email;
        this.name = name;
        this.password = encodedPassword;
        this.userRole = userRole;
    }
}
