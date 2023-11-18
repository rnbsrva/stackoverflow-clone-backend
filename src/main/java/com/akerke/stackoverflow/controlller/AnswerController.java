package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.domain.dto.AnswerDTO;
import com.akerke.stackoverflow.domain.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.domain.service.AnswerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import static com.akerke.stackoverflow.common.validate.Validator.validate;

@RestController
@RequestMapping("answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping()
    ResponseEntity<?> save (
            @Valid
            @RequestBody AnswerDTO answerDTO,
            BindingResult bindingResult
    ) {
        validate(bindingResult);
        return ResponseEntity.status(HttpStatus.CREATED).body(answerService.save(answerDTO));
    }

    @GetMapping()
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(answerService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(answerService.getById(id));
    }

    @PutMapping("{id}")
    ResponseEntity<?> update (
            @PathVariable Long id,
            @Valid
            @RequestBody AnswerUpdateDTO answerUpdateDTO,
            BindingResult bindingResult
    ) {
        validate(bindingResult);
        return ResponseEntity.accepted().body(answerService.update(answerUpdateDTO, id));
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete(
            @PathVariable Long id
    ){
        answerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
