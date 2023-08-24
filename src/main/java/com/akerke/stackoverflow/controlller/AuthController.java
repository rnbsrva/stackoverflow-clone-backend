package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("register")
    String register(
            @RequestBody UserDTO userDTO
    ) {
         ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.save(userDTO));
        return "Added Successfully";
    }

//    @PostMapping("refresh-token")
//    ResponseEntity<?> refresh(
//            @RequestHeader("refresh_token") String refreshToken
//    ) {
//        return
//                ResponseEntity
//                        .ok(userService.refresh(refreshToken));
//    }

//    @PostMapping
//    ResponseEntity<?> auth(
//            @RequestBody AuthRequest authRequest
//    ) {
//        return ResponseEntity
//                .ok(userService.auth(authRequest));
//    }

}
