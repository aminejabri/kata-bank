package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.domain.common.mappers.DomainMapper;
import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountDocumentMapper<E extends BankAccount> extends DomainMapper<E, BankAccountDocument, AccountType> {

  boolean supports(AccountType accountType);
}
