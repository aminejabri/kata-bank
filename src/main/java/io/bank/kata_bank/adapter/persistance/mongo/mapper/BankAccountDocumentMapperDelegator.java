package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.adapter.common.mapping.GenericMapperDelegator;
import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import org.springframework.stereotype.Component;

@Component("documentMapperDelegator")
public class BankAccountDocumentMapperDelegator extends
    GenericMapperDelegator<BankAccount, BankAccountDocument, AccountType>
    implements BankAccountMapperDelegator {

  /*
   * suppressing warnings for unchecked casts and raw types, check will be done at application startup
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public BankAccountDocumentMapperDelegator(
      List<? extends BankAccountDocumentMapper<? extends BankAccount>> entityMappers) {
    super((List) entityMappers);
  }
}
