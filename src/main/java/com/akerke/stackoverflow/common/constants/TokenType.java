package com.akerke.stackoverflow.common.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.jsonwebtoken.SignatureAlgorithm.*;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS(60, HS384), // 1hour
    RESET_PASSWORD(5, HS384), //5 minutes
    REFRESH(1440, HS512); // 1day

        private final Integer expirationMinute;
    private final SignatureAlgorithm algorithm;
}
