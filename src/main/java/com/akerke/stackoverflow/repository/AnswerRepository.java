package com.akerke.stackoverflow.repository;

import com.akerke.stackoverflow.entity.Answer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends MongoRepository<Answer, Long> {


}
