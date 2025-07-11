package io.bank.kata_bank.adapter.persistance.jpa;

import io.bank.kata_bank.adapter.persistance.jpa.model.BankAccountEntity;
import io.bank.kata_bank.adapter.persistance.jpa.repository.BankAccountJpaRepository;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import java.math.BigDecimal;
import java.util.Random;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

@Configuration
@ConditionalOnProperty(name = "kata.bank.adapter.persistance.jpa.enabled", havingValue = "true")
@Import({DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class JpaAutoConfigurationConditional {

  @Bean
  @ConditionalOnBooleanProperty(name = "kata.bank.adapter.persistance.jpa.enabled")
  CommandLineRunner insertInitialData(BankAccountJpaRepository repository) {
    return args -> save(repository);
  }

  @Transactional
  void save(BankAccountJpaRepository repository) {
    // random acount number, use a java random, not a constant
    var accountNumber = "FR76" + new Random().nextInt(1000, 9999) + " 3000 6000 0112 3456 7890 " + new Random().nextInt(100, 200);
    var entity = BankAccountEntity.builder()
        .accountNumber(accountNumber)
        .accountType(AccountType.CURRENT)
        .accountHolderName("John Doe")
        .balance(BigDecimal.TWO)
        .build();
    repository.saveAndFlush(entity);
  }
}
