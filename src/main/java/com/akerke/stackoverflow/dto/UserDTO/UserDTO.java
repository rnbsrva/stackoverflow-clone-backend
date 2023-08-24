package com.akerke.stackoverflow.dto.UserDTO;

import jakarta.validation.constraints.Email;
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
