package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.dto.CommentUpdateDTO;
import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.service.CommentService;
import com.akerke.stackoverflow.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.akerke.stackoverflow.validate.Validator.validate;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping()
    ResponseEntity<?> getAll() {
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ) {
        return ResponseEntity.ok(commentService.getById(id));
    }

    @PostMapping()
    ResponseEntity<?> save(
            @Valid
            @RequestBody CommentDTO commentDTO,
            BindingResult bindingResult
    ) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentDTO));
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(
            @Valid
            @RequestBody CommentUpdateDTO commentUpdateDTO,
            BindingResult bindingResult,
            @PathVariable Long id
    ) {
        validate(bindingResult);
        commentService.update(commentUpdateDTO, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ) {
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
