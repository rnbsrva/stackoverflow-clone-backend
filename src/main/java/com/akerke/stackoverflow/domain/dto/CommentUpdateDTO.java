package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDTO(
        @NotBlank
        String content
) {
}
