package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.findById(id));
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody UserUpdateDTO userUpdateDTO
    ) {
        userService.update(userUpdateDTO, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteById(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.accepted().build();
    }

}
