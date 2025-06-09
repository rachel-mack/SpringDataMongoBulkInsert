package com.mongodb.examples.springdatabulkinsert;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;


@Configuration
public class MongoConfig   {
    @Value("${mongodb.uri}")
    private String uri;
    @Value("${mongodb.database}")
    private String databaseName;

    @Value("${mongodb.atlas}")
    private boolean atlas;

    @Bean
    public MongoClient mongo() {

        ConnectionString connectionString = new ConnectionString(uri);

            MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .applyToSslSettings(builder -> {
                        builder.enabled(true);
                    })
                    .build();


        return MongoClients.create(mongoClientSettings);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        return new MongoTemplate(mongo(), databaseName);
    }
}
