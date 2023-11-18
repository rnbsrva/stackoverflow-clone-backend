package com.akerke.stackoverflow.domain.service;

import com.akerke.stackoverflow.domain.dto.AnswerDTO;
import com.akerke.stackoverflow.domain.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Answer;

import java.util.List;

public interface AnswerService {

    List<Answer> getAll ();

    Answer getById(Long id);

    Answer save (AnswerDTO answerDTO);

    Answer update(AnswerUpdateDTO answerUpdateDTO, Long id);

    void delete (Long id);

    boolean likeAnswer (Long id);

}
