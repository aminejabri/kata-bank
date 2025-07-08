package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.AccountType;
import io.bank.kata_bank.domain.model.BankAccount;

public interface BankAccountDtoMapper<E extends BankAccount> {

  boolean supports(AccountType accountType);

  BankAccountDto toDto(E bankAccount);

  E toEntity(BankAccountDto bankAccountDto);
}
