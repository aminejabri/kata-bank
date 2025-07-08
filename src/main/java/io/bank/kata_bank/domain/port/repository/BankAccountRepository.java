package io.bank.kata_bank.domain.port.repository;


import io.bank.kata_bank.domain.model.BankAccount;
import java.util.Optional;

public interface BankAccountRepository {

  Optional<BankAccount> getAccountById(Long id);

  void save(BankAccount account);
}
