package com.akerke.stackoverflow.domain.repository;

import com.akerke.stackoverflow.domain.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, Long> {


}
