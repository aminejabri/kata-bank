package io.bank.kata_bank.domain.model;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import java.math.BigDecimal;
import java.time.Instant;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@DomainEntity
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankOperation {

  protected Long id;
  protected BankOperationType type;
  protected BankAccount bankAccount;
  protected String description;
  protected BigDecimal amount;
  protected Instant createdAt;
}
