package com.akerke.stackoverflow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
public class Comment {
    @Id
    private String id;
    @DBRef(db = "sofdb")
    @Field(name = "comment_user")
    private User user;
    @DBRef(db = "sofdb")
    @Field(name = "comment_to_question")
    private Question question;
    private String description;
}
