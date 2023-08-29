package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.model.Comment;
import com.akerke.stackoverflow.model.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
}
