package com.akerke.stackoverflow.mapper;

import com.akerke.stackoverflow.dto.AnswerDTO;
import com.akerke.stackoverflow.dto.AnswerUpdateDTO;
import com.akerke.stackoverflow.model.Answer;
import com.akerke.stackoverflow.model.Comment;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.User;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Mapper(imports = {
        ArrayList.class, Question.class,
        User.class, HashSet.class, Comment.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AnswerMapper {

    @Mapping(target = "comments", expression = "java(new ArrayList<Comment>())")
    @Mapping(target = "likedUsers", expression = "java(new HashSet<User>())")
    Answer toModel (AnswerDTO answerDTO);

    AnswerDTO toDTO (Answer answer);

    @Mapping(target = "id", ignore = true)
    void update (AnswerUpdateDTO answerUpdateDTO, @MappingTarget Answer answer);
}
