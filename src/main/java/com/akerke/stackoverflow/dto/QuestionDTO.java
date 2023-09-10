package com.akerke.stackoverflow.dto;

import java.util.List;

public record QuestionDTO(
        Long userId,
        String title,
        String description,
        List<Long> tagIds
) {
}
