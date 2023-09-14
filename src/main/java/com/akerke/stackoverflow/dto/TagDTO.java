package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record TagDTO(
        @NotBlank
        String title
) {
}
