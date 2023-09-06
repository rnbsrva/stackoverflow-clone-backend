package com.akerke.stackoverflow.mapper;

import com.akerke.stackoverflow.dto.CommentDTO;
import com.akerke.stackoverflow.model.Answer;
import com.akerke.stackoverflow.model.Comment;
import com.akerke.stackoverflow.model.Question;
import com.akerke.stackoverflow.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.ArrayList;

@Mapper(imports = {
        ArrayList.class, Question.class,
        Comment.class},
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CommentMapper {

    Comment toModel(CommentDTO commentDTO);

    @Mapping(target = "userId", expression = "java(comment.getUser().getId())")
    @Mapping(target = "questionId", expression = "java(comment.getQuestion().getId())")
    CommentDTO toDTO (Comment comment);



}
