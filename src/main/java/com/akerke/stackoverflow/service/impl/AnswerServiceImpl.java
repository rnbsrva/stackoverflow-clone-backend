package com.akerke.stackoverflow.service.impl;

import com.akerke.stackoverflow.dto.AnswerDTO;
import com.akerke.stackoverflow.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.exception.EntityNotFoundException;
import com.akerke.stackoverflow.mapper.AnswerMapper;
import com.akerke.stackoverflow.model.Answer;
import com.akerke.stackoverflow.repository.AnswerRepository;
import com.akerke.stackoverflow.service.AnswerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerRepository answerRepository;
    private final AnswerMapper answerMapper;

    @Override
    public List<Answer> getAll() {
        return answerRepository.findAll();
    }

    @Override
    public Answer getById(Long id) {
        return answerRepository.findById(id).orElseThrow(()-> new EntityNotFoundException(Answer.class, id));
    }

    @Override
    public Answer save(AnswerDTO answerDTO) {
        return answerRepository.save(answerMapper.toModel(answerDTO));
    }

    @Override
    public Answer update(AnswerUpdateDTO answerUpdateDTO, Long id) {
        Answer answer = this.getById(id);
        answerMapper.update(answerUpdateDTO, answer);
        return answerRepository.save(answer);
    }

    @Override
    public void delete(Long id) {
        Answer answer = this.getById(id);
        answerRepository.delete(answer);
    }
}
