package com.akerke.stackoverflow.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Document(collection = "answer")
public class Answer {
    @Id
    private Long id;
    @ManyToOne
    private Question question;
    @ManyToOne
    private User user;
    private String description;
    @ManyToMany
    @JoinTable(
            name = "answer_user",
            joinColumns = @JoinColumn(name = "answer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id")
    )
    private Set<User> likedUsers;
    @OneToMany(
            mappedBy = "answer",
            cascade = CascadeType.ALL
    )
    private List<CommentAnswer> comments;
}
