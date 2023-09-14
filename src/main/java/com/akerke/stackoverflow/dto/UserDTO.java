package com.akerke.stackoverflow.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record UserDTO(
        @NotBlank @Email
        String email,
        @NotBlank @Max(value = 25)
        String name,
        @NotBlank @Max(value = 50)
        String surname,
        @NotBlank
        String password,
        Date sentAt
) {
    public UserDTO(String email, String name, String surname, String password) {
        this(
                email,
                name,
                surname,
                password,
                new Date()
        );

    }

    public boolean isSentWithin5Minutes() {
        Date now = new Date();
        long timeDifferenceInMillis = now.getTime() - sentAt.getTime();
        long minutesDifference = timeDifferenceInMillis / (60 * 1000);
        return minutesDifference <= 5;
    }
}