package io.bank.kata_bank.domain.model.bank_operation;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;

@ValueObject
@Getter
public abstract class BankOperation {

  private final Instant timestamp = Instant.now();
  private final BankAccount bankAccount;
  private final BigDecimal amount;

  protected BankOperation(BankAccount bankAccount, BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidBankOperationException("Amount must be positive");
    }
    if (bankAccount == null) {
      throw new InvalidBankOperationException("Bank account must be provided");
    }
    this.bankAccount = bankAccount;
    this.amount = amount;
  }

  public abstract BankOperationType getType();
}
