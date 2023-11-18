package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.domain.dto.QuestionDTO;
import com.akerke.stackoverflow.domain.dto.QuestionUpdateDTO;
import com.akerke.stackoverflow.domain.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.akerke.stackoverflow.common.validate.Validator.validate;

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
            @Valid
            @RequestBody QuestionDTO questionDTO,
            BindingResult bindingResult
    ) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.save(questionDTO));
    }

    @PatchMapping("accept-answer")
    ResponseEntity<?> acceptAnswer (
            @RequestParam Long questionId,
            @RequestParam Long answerId
            ){
        boolean res = questionService.acceptAnswer(questionId, answerId);
        if(res)
            return ResponseEntity.accepted().build();
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    @PutMapping("{id}")
    ResponseEntity<?> update (
            @Valid
            @RequestBody QuestionUpdateDTO questionUpdateDTO,
            @PathVariable Long id
            ){
        questionService.update(questionUpdateDTO, id);
        return ResponseEntity.accepted().build();
    }



    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        questionService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
