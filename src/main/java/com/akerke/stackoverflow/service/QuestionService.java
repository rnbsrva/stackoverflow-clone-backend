package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.dto.QuestionUpdateDTO;
import com.akerke.stackoverflow.entity.Question;

import java.util.List;

public interface QuestionService {

     List<Question> getAll();
    Question getById(Long id);

     Question save (QuestionDTO questionDTO);

     boolean acceptAnswer(Long questionId, Long answerId);

     void update(QuestionUpdateDTO questionUpdateDTO, Long id);

     boolean deleteById (Long id);




}
