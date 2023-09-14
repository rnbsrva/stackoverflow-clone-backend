package com.akerke.stackoverflow.util;

import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MongoUtils {

    private final MongoTemplate mongoTemplate;

    public void removeItem(Long documentId, Object itemToRemove, Class<?> T, String listName) {
        Query query = new Query(Criteria.where("_id").is(documentId));
        Update update = new Update().pull(listName, itemToRemove);
        mongoTemplate.updateFirst(query, update, T);
    }

}
