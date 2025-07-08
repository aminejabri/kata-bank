package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.AccountType;
import io.bank.kata_bank.domain.model.CurrentAccount;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;

@Mapper(componentModel = ComponentModel.SPRING)
public interface CurrentAccountDtoMapper extends BankAccountDtoMapper<CurrentAccount> {

  default boolean supports(AccountType accountType) {
    return AccountType.CURRENT.equals(accountType);
  }

  BankAccountDto toDto(CurrentAccount bankAccount);

  CurrentAccount toEntity(BankAccountDto bankAccountDto);
}
