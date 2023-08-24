package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.model.CommentQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentQuestionRepository extends MongoRepository<CommentQuestion, String> {
}
