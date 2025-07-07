package io.bank.kata_bank.domain.port.repository;


import io.bank.kata_bank.domain.model.Account;
import java.util.Optional;

public interface AccountRepository {

  Optional<Account> getAccountById(Long id);

  void save(Account account);
}
