package io.bank.kata_bank.domain.model.bank_operation;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import java.math.BigDecimal;

@ValueObject
public class Withdrawal extends BankOperation {

  public Withdrawal(BigDecimal amount) {
    super(amount, BankOperationType.WITHDRAWAL);
  }
}

