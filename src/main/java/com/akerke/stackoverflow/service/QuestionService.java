package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.model.Question;

import java.util.List;

public interface QuestionService {

     List<Question> getAll();
    Question getById(Long id);

     Question save (QuestionDTO questionDTO);

     boolean deleteById (Long id);




}
