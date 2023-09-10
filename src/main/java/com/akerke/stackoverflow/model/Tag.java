package com.akerke.stackoverflow.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Document(collection = "tag")
public class Tag {

    @Transient
    public static final String SEQUENCE_NAME = "tag_sequence";
    @Id
    private Long id;
    private String title;
    @Field(name = "tag_questions")
    @DBRef(lazy = true)
    private List<Question> questions;
}
