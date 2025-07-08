package io.bank.kata_bank.domain.model;

import java.math.BigDecimal;
import java.util.List;

public interface BankAccount {

  Long getId();

  AccountType getType();

  String getAccountNumber();

  Client getClient();

  BigDecimal getBalance();

  List<Withdrawal> getWithdrawals();

  void withdraw(Withdrawal withdrawal);
}
