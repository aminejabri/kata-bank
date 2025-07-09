package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.common.mapping.GenericMapperFacade;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("dtoMapperFacade")
public class BankAccountDtoMapperFacade extends
    GenericMapperFacade<BankAccount, BankAccountDto, AccountType>
    implements BankAccountMapperFacade {

  /*
   * suppressing warnings for unchecked casts and raw types, check will be done at runtime
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public BankAccountDtoMapperFacade(
      List<? extends BankAccountDtoMapper<? extends BankAccount>> entityMappers) {
    super((List) entityMappers);
  }
}
