package com.akerke.stackoverflow.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
public class Comment {

    @Transient
    public static final String SEQUENCE_NAME = "comment_sequence";

    @Id
    private Long id;
    @Field(name = "comment_user")
    @DBRef(lazy = true)
    private User user;
    @DBRef(lazy = true)
    @Field(name = "comment_to_question")
    private Question question;
    private String content;
}
