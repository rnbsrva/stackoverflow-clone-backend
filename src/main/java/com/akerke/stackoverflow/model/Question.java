package com.akerke.stackoverflow.model;

import com.akerke.stackoverflow.constants.QuestionStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "question")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Question {

    @Transient
    public static final String SEQUENCE_NAME = "questions_sequence";

    @Id
    private Long id;
    @DBRef(lazy = true)
    private User user;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;
    @Field(name = "answers")
    private List<Answer> answers;
    @Field(name = "question_tags")
    @DBRef(lazy = true)
    private List<Tag> tags;
    @DBRef(lazy = true)
    @Field(name = "question_comments")
    private List<Comment> comments;

}
