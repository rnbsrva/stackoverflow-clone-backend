package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record QuestionDTO(
        @NotNull
        Long userId,
        @NotBlank @Max(value = 100)
        String title,
        @NotBlank
        String description,
        @NotEmpty
        List<Long> tagIds
) {
}
