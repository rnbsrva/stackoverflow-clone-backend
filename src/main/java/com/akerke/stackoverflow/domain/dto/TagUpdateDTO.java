package com.akerke.stackoverflow.domain.dto;

import jakarta.validation.constraints.NotBlank;

public record TagUpdateDTO (
        @NotBlank
        String title
){
}
