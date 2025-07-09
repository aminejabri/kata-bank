package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BasicAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface BasicAccountDtoMapper extends BankAccountDtoMapper<BasicAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.BASIC.equals(accountType);
  }
}
