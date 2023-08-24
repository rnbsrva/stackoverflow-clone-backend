package com.akerke.stackoverflow.model;

import com.akerke.stackoverflow.constants.Role;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Document(collection = "user_")
public class User {

    @Id
    private String id;

    private String name;
    private String surname;
    private String email;
    @JsonIgnore
    private String password;
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Question> questions;
    @Enumerated(EnumType.STRING)
    private Role role;
    private LocalDateTime createdTime = LocalDateTime.now();

    public User() {}

    public User(String name, String surname, String email, String password, List<Question> questions, Role role, LocalDateTime createdTime) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.questions = questions;
        this.role = role;
        this.createdTime = createdTime;
    }
}
