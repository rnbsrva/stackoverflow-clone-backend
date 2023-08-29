package com.akerke.stackoverflow.dto;

public record QuestionDTO(
        Long userId,
        String title,
        String description
) {
}
