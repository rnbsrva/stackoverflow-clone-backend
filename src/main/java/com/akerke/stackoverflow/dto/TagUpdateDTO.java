package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record TagUpdateDTO (
        @NotBlank
        String title
){
}
