package io.bank.kata_bank.application.service;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import io.bank.kata_bank.domain.service.BankAccountService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class DefaultBankAccountService implements BankAccountService {

  private final BankAccountRepository bankAccountRepository;

  @Override
  public BankAccount getBankAccount(UUID id) {
    return bankAccountRepository.getAccountById(id)
        .orElseThrow(() -> new EntityNotFoundException("Account with id " + id + " not found"));
  }
}
