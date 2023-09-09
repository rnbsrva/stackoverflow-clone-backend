package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UserDTO(
        @NotBlank
        String email,
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String password,
        Date sentAt
) {
    public UserDTO(String email, String name, String surname, String password) {
        this(
                email,
                name,
                surname,
                password,
                new Date()
        );
    }
}