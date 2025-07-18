package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.CurrentAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CurrentAccountDocumentMapper extends BankAccountDocumentMapper<CurrentAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.CURRENT.equals(accountType);
  }
}
