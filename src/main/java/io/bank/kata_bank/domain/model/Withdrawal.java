package io.bank.kata_bank.domain.model;

import io.bank.kata_bank.domain.common.annotation.DDD.ValueObject;
import io.bank.kata_bank.domain.common.exception.InvalidWithdrawalException;
import java.math.BigDecimal;
import java.time.Instant;

@ValueObject
public record Withdrawal(Long accountId, BigDecimal amount, String description, Instant createdAt) {

  public Withdrawal {
    createdAt = Instant.now();
    if (accountId == null) {
      throw new InvalidWithdrawalException("Account is required for withdrawal");
    }
    if (amount == null) {
      throw new InvalidWithdrawalException("Withdrawal amount is required");
    }
    if (amount.signum() <= 0) {
      throw new InvalidWithdrawalException("Withdrawal amount must be greater than zero");
    }
  }
}

