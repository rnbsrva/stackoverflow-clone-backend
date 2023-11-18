package com.akerke.stackoverflow.domain.repository;

import com.akerke.stackoverflow.domain.entity.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends MongoRepository<Comment, Long> {
}
