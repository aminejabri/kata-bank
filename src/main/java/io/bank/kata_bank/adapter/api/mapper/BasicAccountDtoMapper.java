package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.AccountType;
import io.bank.kata_bank.domain.model.BasicAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BasicAccountDtoMapper extends BankAccountDtoMapper<BasicAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.BASIC.equals(accountType);
  }

  BankAccountDto toDto(BasicAccount bankAccount);

  BasicAccount toEntity(BankAccountDto bankAccountDto);
}
