
package com.akerke.stackoverflow.domain.repository.listeners;

import com.akerke.stackoverflow.domain.entity.Tag;
import com.akerke.stackoverflow.domain.service.impl.SequenceGeneratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class TagModelListener extends AbstractMongoEventListener<Tag> {

    private final SequenceGeneratorService sequenceGenerator;

    @Override
    public void onBeforeConvert(BeforeConvertEvent<Tag> event) {
        if (event.getSource().getId() == null || event.getSource().getId() < 1 ) {
            event.getSource().setId(sequenceGenerator.generateSequence(Tag.SEQUENCE_NAME));
        }
    }


}