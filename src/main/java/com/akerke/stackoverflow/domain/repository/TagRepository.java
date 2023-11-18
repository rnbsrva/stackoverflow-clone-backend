package com.akerke.stackoverflow.domain.repository;

import com.akerke.stackoverflow.domain.entity.Tag;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends MongoRepository<Tag, Long> {
}
