package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("questions")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping()
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(questionService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(questionService.getById(id));
    }

    @PostMapping()
    ResponseEntity<?> save(
            @RequestBody QuestionDTO questionDTO
            ){
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(questionDTO));
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        return ResponseEntity.accepted().body(questionService.deleteById(id));
    }

}
