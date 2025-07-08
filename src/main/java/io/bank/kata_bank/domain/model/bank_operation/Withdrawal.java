package io.bank.kata_bank.domain.model.bank_operation;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.math.BigDecimal;

@ValueObject
public class Withdrawal extends BankOperation {

  public Withdrawal(BankAccount bankAccount, BigDecimal amount) {
    super(bankAccount, amount);
  }

  @Override
  public BankOperationType getType() {
    return BankOperationType.WITHDRAWAL;
  }
}

