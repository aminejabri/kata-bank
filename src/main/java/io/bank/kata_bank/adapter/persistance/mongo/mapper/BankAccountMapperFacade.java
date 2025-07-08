package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountDocumentMapperFacade {

  <T extends BankAccount> BankAccountDto toDto(T bankAccount);

  BankAccount toEntity(BankAccountDto dto);
}