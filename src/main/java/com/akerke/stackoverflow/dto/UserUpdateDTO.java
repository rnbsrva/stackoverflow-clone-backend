package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.NotBlank;

public record UserUpdateDTO(
        @NotBlank
        String name,
        @NotBlank
        String surname
) {

}
