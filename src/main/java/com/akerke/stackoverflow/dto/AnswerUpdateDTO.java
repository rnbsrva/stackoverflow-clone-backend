package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record AnswerUpdateDTO(
        @NotBlank
        String description
) {

}
