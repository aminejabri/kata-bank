package io.bank.kata_bank.adapter.api.dto;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.Supportable;
import java.util.UUID;

public record BankAccountDto(
    UUID id,
    String accountNumber,
    AccountType type,
    double balance
) implements Supportable<AccountType> {

  @Override
  public AccountType getType() {
    return type;
  }
}
