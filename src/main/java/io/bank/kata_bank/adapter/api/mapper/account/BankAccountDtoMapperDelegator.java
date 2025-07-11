package io.bank.kata_bank.adapter.api.mapper.account;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.common.mapping.GenericMapperDelegator;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("accountDtoMapperDelegator")
public class BankAccountDtoMapperDelegator extends
    GenericMapperDelegator<BankAccount, BankAccountDto, AccountType>
    implements BankAccountMapperDelegator {

  /*
   * suppressing warnings for unchecked casts and raw types, check will be done at runtime
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public BankAccountDtoMapperDelegator(
      List<? extends BankAccountDtoMapper<? extends BankAccount>> entityMappers) {
    super((List) entityMappers);
  }
}
