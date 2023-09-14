package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
}
