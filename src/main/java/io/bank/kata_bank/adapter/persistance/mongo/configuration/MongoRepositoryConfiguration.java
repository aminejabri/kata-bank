package io.bank.kata_bank.adapter.persistance.mongo.configuration;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@Conditional(MongoRepositoryCondition.class)
@Import({MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class MongoRepositoryConfiguration {

  @Bean
  CommandLineRunner insertInitialData(MongoTemplate mongoTemplate) {
    return args -> {
      var doc = BankAccountDocument.builder()
          .id(UUID.randomUUID().toString())
          .accountNumber("FR76 3000 6000 0112 3456 7890 189")
          .accountHolder(MobyNamesGenerator.getRandomName())
          .type(AccountType.CURRENT)
          .balance(BigDecimal.TWO)
          .build();
      mongoTemplate.save(doc);
      mongoTemplate.save(doc);
      mongoTemplate.save(doc);
      mongoTemplate.save(BankAccountDocument.builder()
          .id(UUID.randomUUID().toString())
          .accountNumber("FR76 3000 6000 0112 3456 7890 190")
          .balance(BigDecimal.TWO)
          .type(AccountType.BASIC)
          .accountHolder(MobyNamesGenerator.getRandomName())
          .build());
    };
  }
}
