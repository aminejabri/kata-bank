package io.bank.kata_bank.adapter.api.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.common.mappers.Supportable;
import java.math.BigDecimal;
import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record BankAccountDto(
    UUID id,
    String accountNumber,
    String accountHolder,
    AccountType type,
    BigDecimal balance,
    BigDecimal overdraftLimit,
    BigDecimal savingsCap
) implements Supportable<AccountType> {

  @Override
  public AccountType getType() {
    return type;
  }
}
