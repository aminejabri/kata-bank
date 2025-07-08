package io.bank.kata_bank.adapter.persistance.mongo;

import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BankAccountMongoRepository implements BankAccountRepository {

  private final MongoTemplate mongoTemplate;

  @Override
  public Optional<BankAccount> getAccountById(UUID id) {
    return Optional.empty();
  }

  @Override
  public void save(BankAccount account) {

  }
}
