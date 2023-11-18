package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CommentDTO(
        @NotNull
        Long userId,
        @NotNull
        Long questionId,
        @NotBlank
        String content
) {
}
