package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnswerDTO(
        @NotNull
        Long questionId,
        @NotNull
        Long userId,
        @NotBlank
        String description
) {
}
