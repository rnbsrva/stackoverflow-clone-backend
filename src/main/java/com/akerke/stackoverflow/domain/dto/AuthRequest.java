package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @NotBlank @Email
        String email,
        @NotBlank
        String password
) {
}
