package com.example.team5project.domain.auth.entity;

import lombok.Getter;

@Getter
public class AuthUser {

    private final Long id;
    private final String name;
    private final String email;

    public AuthUser(Long id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
}
