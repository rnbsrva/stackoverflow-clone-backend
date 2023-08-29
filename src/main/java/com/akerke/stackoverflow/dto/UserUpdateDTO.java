package com.akerke.stackoverflow.dto;

import com.akerke.stackoverflow.model.Question;
import jakarta.validation.constraints.NotBlank;

import java.util.ArrayList;

public record UserUpdateDTO(
        @NotBlank
        String email,
        @NotBlank
        String name,
        @NotBlank
        String surname,
        @NotBlank
        String password
) {

}
