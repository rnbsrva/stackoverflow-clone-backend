package com.akerke.stackoverflow.mapper;

import com.akerke.stackoverflow.constants.QuestionStatus;
import com.akerke.stackoverflow.dto.QuestionDTO;
import com.akerke.stackoverflow.dto.QuestionUpdateDTO;
import com.akerke.stackoverflow.dto.UserDTO;
import com.akerke.stackoverflow.entity.*;
import org.mapstruct.*;

import java.util.ArrayList;

@Mapper(imports = {QuestionStatus.class,
        ArrayList.class, Question.class,
        Tag.class, Comment.class, Answer.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface QuestionMapper {

    @Mapping(target = "userId", expression = "java(question.getUser().getId())")
    QuestionDTO toDTO (Question question);

    @Mapping(target = "answers", expression = "java(new ArrayList<Answer>())")
    @Mapping(target = "comments", expression = "java(new ArrayList<Comment>())")
    @Mapping(target = "tags", expression = "java(new ArrayList<Tag>())")
    @Mapping(target = "status", expression = "java(QuestionStatus.QUESTION_NOT_SOLVED)")
    Question toModel (QuestionDTO questionDTO);

    @Mapping(target = "id", ignore = true)
    void update (QuestionUpdateDTO userDTO, @MappingTarget Question question);

}
