package com.akerke.stackoverflow.constants;

import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static io.jsonwebtoken.SignatureAlgorithm.*;

@Getter
@AllArgsConstructor
public enum TokenType {
    ACCESS(60, HS384),  // 1hour
    REFRESH(1440, HS512); // 1day

    private final Integer expirationMinute;
    private final SignatureAlgorithm algorithm;
}
