package com.akerke.stackoverflow.model;

import com.akerke.stackoverflow.constants.QuestionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "question")
public class Question {
    @Id
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private QuestionStatus status;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    private List<Answer> answers;

    @ManyToMany
    @JoinTable(
            name = "question_tag",
            joinColumns = @JoinColumn(name = "question_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id")
    )
    private List<Tag> tags;

    @OneToMany(
            mappedBy = "question",
            cascade = CascadeType.ALL
    )
    private List<CommentAnswer> comments;

}
