package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record UserDTO(
        @NotBlank
        String email,
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String password
) {
}
