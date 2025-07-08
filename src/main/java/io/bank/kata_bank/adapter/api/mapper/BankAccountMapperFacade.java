package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.BankAccount;

public interface BankAccountMapperFacade {

  <T extends BankAccount> BankAccountDto toDto(T bankAccount);

  BankAccount toEntity(BankAccountDto dto);
}