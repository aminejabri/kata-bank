package io.bank.kata_bank.domain.port.repository;


import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BankAccountRepository {

  Optional<BankAccount> getAccountById(UUID id);

  List<BankAccount> getAll();

  void save(BankAccount account);
}
