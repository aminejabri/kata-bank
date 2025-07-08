package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountDocumentMapper<E extends BankAccount> {

  boolean supports(AccountType accountType);

  BankAccountDto toDto(E bankAccount);

  E toEntity(BankAccountDto bankAccountDto);
}
