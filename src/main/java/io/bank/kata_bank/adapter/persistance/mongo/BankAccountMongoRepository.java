package io.bank.kata_bank.adapter.persistance.mongo;

import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.adapter.persistance.mongo.mapper.BankAccountMapperFacade;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class BankAccountMongoRepository implements BankAccountRepository {

  private final MongoTemplate mongoTemplate;
  @Qualifier("documentMapperFacade")
  private final BankAccountMapperFacade bankAccountMapperFacade;

  @Override
  public Optional<BankAccount> getAccountById(UUID id) {
    return Optional.ofNullable(mongoTemplate.findById(id.toString(), BankAccountDocument.class))
        .map(bankAccountMapperFacade::toEntity);
  }

  @Override
  public List<BankAccount> getAll() {
    return mongoTemplate.findAll(BankAccountDocument.class)
        .stream()
        .map(bankAccountMapperFacade::toEntity)
        .toList();
  }

  @Override
  public void save(BankAccount account) {
    mongoTemplate.save(bankAccountMapperFacade.fromEntity(account));
  }
}
