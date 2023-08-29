package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.model.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> getAll();
    public Question getById(Long id);

    public Question save (QuestionDTO questionDTO);

    public boolean deleteById (Long id);

    //update

}
