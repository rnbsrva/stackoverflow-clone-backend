package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.AnswerDTO;
import com.akerke.stackoverflow.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("answers")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @PostMapping()
    ResponseEntity<?> save (AnswerDTO answerDTO) {
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
            @RequestBody AnswerUpdateDTO answerUpdateDTO
            ){
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
