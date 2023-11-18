package com.akerke.stackoverflow.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Set;

@Getter
@Setter
@Document(collection = "answer")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Answer {

    @Transient
    public static final String SEQUENCE_NAME = "answers_sequence";
    @Id
    private Long id;

    @Field(name = "answer_question")
    @DBRef(lazy = true)
    private Question question;

    @Field(name = "answer_user")
    @DBRef(lazy = true)
    private User user;

    private String description;

    @Field(name = "liked_users")
    @DBRef(lazy = true)
    private Set<User> likedUsers;

}
