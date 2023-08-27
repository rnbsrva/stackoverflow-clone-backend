package com.akerke.stackoverflow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@Document(collection = "answer")
public class Answer {
    @Id
    private String id;
    @Field(name = "answer_question")
    private Question question;
    @Field(name = "answer_user")
    private User user;
    private String description;
    @Field(name = "answer_users")
    private Set<User> likedUsers;
    @Field(name = "answer_comments")
    private List<Comment> comments;
}
