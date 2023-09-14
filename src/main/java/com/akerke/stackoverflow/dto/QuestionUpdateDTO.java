package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

public record QuestionUpdateDTO(
        @NotBlank @Max(value = 100)
        String title,
        @NotBlank
        String description
) {
}
