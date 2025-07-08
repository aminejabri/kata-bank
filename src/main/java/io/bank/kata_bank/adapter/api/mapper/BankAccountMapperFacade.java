package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountMapperFacade {

  <T extends BankAccount> BankAccountDto fromEntity(T bankAccount);

  BankAccount toEntity(BankAccountDto dto);
}