package com.akerke.stackoverflow.dto;

public record AuthRequest(
        String email,
        String password
) {
}
