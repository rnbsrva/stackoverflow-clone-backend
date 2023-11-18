package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.QuestionDTO;
import com.akerke.stackoverflow.domain.dto.QuestionUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Question;

import java.util.List;

public interface QuestionService {

     List<Question> getAll();
    Question getById(Long id);

     Question save (QuestionDTO questionDTO);

     boolean acceptAnswer(Long questionId, Long answerId);

     void update(QuestionUpdateDTO questionUpdateDTO, Long id);

     boolean deleteById (Long id);




}
