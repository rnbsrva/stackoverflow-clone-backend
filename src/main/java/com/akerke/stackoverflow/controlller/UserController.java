package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.UserUpdateDTO;
import com.akerke.stackoverflow.service.QuestionService;
import com.akerke.stackoverflow.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.akerke.stackoverflow.validate.Validator.validate;


@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(userService.getById(id));
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(
            @PathVariable Long id,
            @Valid
            @RequestBody UserUpdateDTO userUpdateDTO,
            BindingResult bindingResult
    ) {
        validate(bindingResult);
        userService.update(userUpdateDTO, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> deleteById(
            @PathVariable Long id
    ) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

}
