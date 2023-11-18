package com.akerke.stackoverflow.domain.mapper;

import com.akerke.stackoverflow.domain.dto.AnswerDTO;
import com.akerke.stackoverflow.domain.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.domain.entity.Answer;
import com.akerke.stackoverflow.domain.entity.Comment;
import com.akerke.stackoverflow.domain.entity.User;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.HashSet;

@Mapper(imports = {
        ArrayList.class,
        User.class, HashSet.class, Comment.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AnswerMapper {

    @Mapping(target = "likedUsers", expression = "java(new HashSet<User>())")
    Answer toModel (AnswerDTO answerDTO);

    AnswerDTO toDTO (Answer answer);

    @Mapping(target = "id", ignore = true)
    void update (AnswerUpdateDTO answerUpdateDTO, @MappingTarget Answer answer);
}
