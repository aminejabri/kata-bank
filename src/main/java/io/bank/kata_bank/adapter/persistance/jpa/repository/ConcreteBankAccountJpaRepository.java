package io.bank.kata_bank.adapter.persistance.jpa.repository;

import io.bank.kata_bank.adapter.persistance.mongo.mapper.BankAccountMapperDelegator;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "kata.bank.adapter.persistance.jpa.enabled", havingValue = "true")
@AllArgsConstructor
public class ConcreteBankAccountJpaRepository implements BankAccountRepository {

  private final BankAccountJpaRepository bankAccountJpaRepository;
  private final BankAccountMapperDelegator bankAccountMapperDelegator;

  @Override
  public Optional<BankAccount> getAccountById(UUID id) {
    //    return bankAccountJpaRepository.findById(id)
    //        .map(bankAccountMapperFacade::toEntity);
    return Optional.empty();
  }

  @Override
  public List<BankAccount> getAll() {
    return List.of();
  }

  @Override
  public void save(BankAccount account) {

  }
}
