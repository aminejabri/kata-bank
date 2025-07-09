package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.common.mapping.EntityMapper;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountDtoMapper<E extends BankAccount> extends
    EntityMapper<E, BankAccountDto, AccountType> {

  boolean supports(AccountType accountType);

  BankAccountDto toDto(E bankAccount);

  E toEntity(BankAccountDto bankAccountDto);
}
