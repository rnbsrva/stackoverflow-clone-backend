package com.akerke.stackoverflow.domain.entity;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "database_sequences")
@Getter
@Setter
@NoArgsConstructor
public class DatabaseSequence {

    @Id
    private String id;

    private long seq;
}