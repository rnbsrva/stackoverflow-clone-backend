package com.akerke.stackoverflow.model;

import com.akerke.stackoverflow.constants.QuestionStatus;
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
public class Question {
    @Id
    private String id;
    @DBRef
    private User user;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;
    @DBRef(db = "sofdb")
    @Field(name = "answers")
    private List<Answer> answers;
    @DBRef(db = "sofdb")
    @Field(name = "question_tags")
    private List<Tag> tags;
    @DBRef(db = "sofdb")
    @Field(name = "question_comments")
    private List<CommentQuestion> comments;

}
