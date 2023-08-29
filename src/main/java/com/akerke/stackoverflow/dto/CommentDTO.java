package com.akerke.stackoverflow.dto;

public record CommentDTO(
        Long userId,
        Long questionId,
        String content
) {
}
