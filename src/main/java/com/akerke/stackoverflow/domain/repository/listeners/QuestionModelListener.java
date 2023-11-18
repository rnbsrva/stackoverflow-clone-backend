package com.akerke.stackoverflow.domain.repository.listeners;

import com.akerke.stackoverflow.domain.entity.Question;
import com.akerke.stackoverflow.domain.service.impl.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class QuestionModelListener extends AbstractMongoEventListener<Question> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Question> event) {
        if (event.getSource().getId() == null || event.getSource().getId() < 1 ) {
            event.getSource().setId(sequenceGenerator.generateSequence(Question.SEQUENCE_NAME));
        }
    }


}