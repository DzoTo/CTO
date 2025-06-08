package com.example.cto.model.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ROLE_USER("ROLE_USER"),
    ROLE_ADMIN("ROLE_ADMIN");
    private String name;
    private UserRole(String name) {
        this.name = name;
    }
}

