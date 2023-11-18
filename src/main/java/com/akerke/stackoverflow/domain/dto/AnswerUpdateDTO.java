package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record AnswerUpdateDTO(
        @NotBlank
        String description
) {

}
