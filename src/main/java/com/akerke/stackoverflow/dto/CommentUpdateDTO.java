package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record CommentUpdateDTO(
        @NotBlank
        String content
) {
}
