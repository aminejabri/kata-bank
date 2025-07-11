package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import java.util.UUID;

public interface BankAccountService {

  BankAccount getBankAccount(UUID id);

  List<BankAccount> getAllBankAccounts();

  void saveBankAccount(BankAccount bankAccount);
}
