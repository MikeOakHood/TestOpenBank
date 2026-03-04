package com.capgemini.test.code.model;

import com.fasterxml.jackson.annotation.JsonCreator;

public enum Role {
    ADMIN,
    SUPERADMIN;

    @JsonCreator
    public static Role from(String value) {
        return Role.valueOf(value.toUpperCase());
    }

    public static Role fromString(String value) {
        try {
            return Role.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid role: " + value);
        }
    }
}
