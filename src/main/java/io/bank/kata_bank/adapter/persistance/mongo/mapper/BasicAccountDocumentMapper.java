package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BasicAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BasicAccountDocumentMapper extends BankAccountDocumentMapper<BasicAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.BASIC.equals(accountType);
  }

  BankAccountDto toDto(BasicAccount bankAccount);

  BasicAccount toEntity(BankAccountDto bankAccountDto);
}
