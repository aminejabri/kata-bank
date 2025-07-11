package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.SavingAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface SavingAccountDocumentMapper extends BankAccountDocumentMapper<SavingAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.SAVING.equals(accountType);
  }
}
