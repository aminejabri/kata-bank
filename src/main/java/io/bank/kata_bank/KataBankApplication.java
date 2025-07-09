package io.bank.kata_bank;

import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import java.math.BigDecimal;
import java.util.UUID;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication(exclude = {
    org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class,
    org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration.class
})
public class KataBankApplication {

  public static void main(String[] args) {
    SpringApplication.run(KataBankApplication.class, args);
  }

  @Bean
  CommandLineRunner insertInitialData(MongoTemplate mongoTemplate) {
    return args -> {
      var doc = BankAccountDocument.builder()
          .id(UUID.randomUUID().toString())
          .accountNumber("FR76 3000 6000 0112 3456 7890 189")
          .accountType(AccountType.CURRENT)
          .balance(BigDecimal.TWO)
          .build();
      mongoTemplate.save(doc);
      mongoTemplate.save(doc);
      mongoTemplate.save(doc);
      mongoTemplate.save(BankAccountDocument.builder()
          .id(UUID.randomUUID().toString())
          .accountNumber("FR76 3000 6000 0112 3456 7890 190")
          .balance(BigDecimal.TWO)
          .accountType(AccountType.BASIC)
          .build());
    };
  }
}
