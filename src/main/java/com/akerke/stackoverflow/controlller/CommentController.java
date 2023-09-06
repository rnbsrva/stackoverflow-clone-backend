package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.service.CommentService;
import com.akerke.stackoverflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping()
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(commentService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(commentService.getById(id));
    }

    @PostMapping()
    ResponseEntity<?> save(
            @RequestBody CommentDTO commentDTO
    ){
        return ResponseEntity.status(HttpStatus.CREATED).body(commentService.save(commentDTO));
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        commentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
