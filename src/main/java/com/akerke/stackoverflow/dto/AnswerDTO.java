package com.akerke.stackoverflow.dto;

public record AnswerDTO(
        Long questionId,
        Long userId,
        String description
) {
}
