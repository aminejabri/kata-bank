package io.bank.kata_bank.adapter.persistance.mongo.repository;

import io.bank.kata_bank.adapter.persistance.mongo.configuration.MongoRepositoryCondition;
import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.adapter.persistance.mongo.mapper.BankAccountMapperDelegator;
import io.bank.kata_bank.domain.common.exception.AccountStateConflictException;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Conditional;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
@Conditional(MongoRepositoryCondition.class)
public class BankAccountMongoRepository implements BankAccountRepository {

  @Qualifier("documentMapperDelegator")
  private final BankAccountMapperDelegator bankAccountMapperDelegator;
  private final MongoTemplate mongoTemplate;

  @Override
  public Optional<BankAccount> getAccountById(UUID id) {
    return Optional.ofNullable(mongoTemplate.findById(id.toString(), BankAccountDocument.class))
        .map(bankAccountMapperDelegator::toDomain);
  }

  @Override
  public List<BankAccount> getAll() {
    return mongoTemplate.findAll(BankAccountDocument.class)
        .stream()
        .map(bankAccountMapperDelegator::toDomain)
        .toList();
  }

  @Override
  public void save(BankAccount account) {
    try {
      mongoTemplate.save(bankAccountMapperDelegator.fromDomain(account));
    } catch (OptimisticLockingFailureException e) {
      throw new AccountStateConflictException(account.getId().toString());
    }
  }
}
