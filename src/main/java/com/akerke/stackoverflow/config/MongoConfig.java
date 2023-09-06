package com.akerke.stackoverflow.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@Configuration
@EnableMongoRepositories(basePackages = "com.akerke.stackoverflow.repository")
public class MongoConfig {


    private @Value("${spring.data.mongodb.database}") String db;
    private @Value("${spring.data.mongodb.uri}") String uri;
    @Bean
    public MongoClient mongo() {
        final ConnectionString connectionString = new ConnectionString(uri+db);
        final MongoClientSettings mongoClientSettings = MongoClientSettings.builder().applyConnectionString(connectionString).build();
        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        return new MongoTemplate(mongo(), db);
    }

}