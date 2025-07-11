package io.bank.kata_bank.domain.model.bank_operation;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import java.math.BigDecimal;
import java.time.Instant;

@ValueObject
public record BankOperation(
    Instant timestamp,
    BigDecimal amount,
    BankOperationType type) {

  public BankOperation(BigDecimal amount, BankOperationType operationType) {
    this(Instant.now(), amount, operationType);
  }

  public BankOperation {
    if (timestamp == null) {
      throw new InvalidBankOperationException("Timestamp cannot be null");
    }
    if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidBankOperationException("Amount must be positive and cannot be null");
    }
    if (type == null) {
      throw new InvalidBankOperationException("Operation type cannot be null");
    }
  }
}
