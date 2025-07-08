package io.bank.kata_bank.domain.model.bank_account;

import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface BankAccount extends Supportable<AccountType> {

  UUID getId();

  AccountType getType();

  String getAccountNumber();

  Client getClient();

  BigDecimal getBalance();

  List<BankOperation> getOperations();

  void withdraw(BigDecimal amount);

  void deposit(BigDecimal amount);
}
