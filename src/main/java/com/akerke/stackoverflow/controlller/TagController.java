package com.akerke.stackoverflow.controlller;

import com.akerke.stackoverflow.dto.TagDTO;
import com.akerke.stackoverflow.dto.TagUpdateDTO;
import com.akerke.stackoverflow.service.TagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("tags")
@RequiredArgsConstructor
public class TagController {

    private final TagService tagService;

    @GetMapping()
    ResponseEntity<?> getAll(){
        return ResponseEntity.ok(tagService.getAll());
    }

    @GetMapping("{id}")
    ResponseEntity<?> getById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(tagService.getById(id));
    }


    @PostMapping()
    ResponseEntity<?> save (
            @RequestBody TagDTO tagDTO
            ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(tagService.save(tagDTO));
    }

    @PutMapping("{id}")
    ResponseEntity<?> update(
            @PathVariable Long id,
            @RequestBody TagUpdateDTO tagUpdateDTO
            ){
        tagService.update(tagUpdateDTO, id);
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("{id}")
    ResponseEntity<?> delete (
            @PathVariable Long id
    ){
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }



}