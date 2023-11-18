package com.akerke.stackoverflow.domain.repository;

import com.akerke.stackoverflow.domain.entity.Question;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<Question, Long> {
}
