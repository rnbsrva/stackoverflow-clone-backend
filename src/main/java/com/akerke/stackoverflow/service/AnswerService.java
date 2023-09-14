package com.akerke.stackoverflow.service;

import com.akerke.stackoverflow.dto.AnswerDTO;
import com.akerke.stackoverflow.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.entity.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAll ();

    Answer getById(Long id);

    Answer save (AnswerDTO answerDTO);

    Answer update(AnswerUpdateDTO answerUpdateDTO, Long id);

    void delete (Long id);

    boolean likeAnswer (Long id);

}
