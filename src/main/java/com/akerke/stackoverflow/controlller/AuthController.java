package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.AuthRequest;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.service.AuthService;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("register")
    ResponseEntity<?> register(
            @RequestBody UserDTO userDTO
    ) {
        authService.register(
                new UserDTO(
                        userDTO.email(),
                        userDTO.name(),
                        userDTO.surname(),
                        userDTO.password())
        );
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @GetMapping("verification")
    ResponseEntity<?> confirm(
            @RequestParam String data
    ) {

        return ResponseEntity.ofNullable(authService.save(data));
    }

    @PostMapping("refresh-token")
    ResponseEntity<?> refresh(
            @RequestHeader("refresh_token") String refreshToken
    ) {
        return
                ResponseEntity
                        .ok(authService.refresh(refreshToken));
    }

    @GetMapping("forgot-password")
    ResponseEntity<?> forgotPassword(
            @RequestParam String email
    ) {
        authService.forgotPassword(email);
        return ResponseEntity.ok().build();
    }

    @PostMapping("reset-password")
    ResponseEntity<?> resetPassword(
            @RequestParam String data,
            @RequestHeader String newPassword
    ) {
        authService.resetPassword(data, newPassword);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    ResponseEntity<?> auth(
            @RequestBody AuthRequest authRequest
    ) {
        return ResponseEntity
                .ok(authService.auth(authRequest));
    }

}
