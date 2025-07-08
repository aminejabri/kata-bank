package io.bank.kata_bank.domain.model;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InvalidWithdrawalException;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;

@DomainEntity
@AllArgsConstructor(access = lombok.AccessLevel.PROTECTED)
@Builder(toBuilder = true)
public class Withdrawal extends BankOperation {

  protected Withdrawal(Long accountId, BigDecimal amount, String description) {
    var createdAt = Instant.now();
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

