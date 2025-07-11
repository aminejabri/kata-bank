package io.bank.kata_bank.domain.model.bank_operation;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.model.bank_account.Supportable;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.Getter;

@ValueObject
@Getter
public abstract class BankOperation implements Supportable<BankOperationType> {

  private final Instant timestamp = Instant.now();
  private final BigDecimal amount;
  protected final BankOperationType operationType;

  protected BankOperation(BigDecimal amount, BankOperationType operationType) {
    if (operationType == null) {
      throw new InvalidBankOperationException("Operation type cannot be null");
    }
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidBankOperationException("Amount must be positive");
    }
    this.operationType = operationType;
    this.amount = amount;
  }

  public BankOperationType getType() {
    return operationType;
  }
}
