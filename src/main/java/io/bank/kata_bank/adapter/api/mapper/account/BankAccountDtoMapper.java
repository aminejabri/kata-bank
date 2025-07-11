package io.bank.kata_bank.adapter.api.mapper.account;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.common.mapping.DomainMapper;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountDtoMapper<E extends BankAccount> extends
    DomainMapper<E, BankAccountDto, AccountType> {

  boolean supports(AccountType accountType);

  BankAccountDto toDto(E bankAccount);

  E toDomain(BankAccountDto bankAccountDto);
}
