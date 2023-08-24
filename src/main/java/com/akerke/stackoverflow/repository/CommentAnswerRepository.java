package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.model.CommentAnswer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentAnswerRepository extends MongoRepository<CommentAnswer, String> {
}
