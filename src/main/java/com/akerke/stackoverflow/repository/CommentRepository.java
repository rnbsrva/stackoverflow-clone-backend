package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
}
