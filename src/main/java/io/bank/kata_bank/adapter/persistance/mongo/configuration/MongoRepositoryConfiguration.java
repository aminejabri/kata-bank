package io.bank.kata_bank.adapter.persistance.mongo.configuration;

import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Conditional(MongoRepositoryCondition.class)
@Import({MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MongoRepositoryConfiguration {

}
