package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.BankAccount;

public interface BankAccountService {

  BankAccount getBankAccount(Long id);
}
