package io.bank.kata_bank.domain.model.bank_account;

import static io.bank.kata_bank.domain.model.bank_account.AccountType.CURRENT;
import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.DEPOSIT;
import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.WITHDRAWAL;

import io.bank.kata_bank.domain.common.annotation.DDD.AggregateRoot;
import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@DomainEntity
@AggregateRoot
@EqualsAndHashCode(callSuper = true)
@Getter
public class CurrentAccount extends BankAccount {

  private final BigDecimal overdraftLimit;

  public CurrentAccount(UUID id,
      String accountNumber,
      String accountHolder,
      BigDecimal balance,
      List<BankOperation> operations,
      Long version,
      BigDecimal overdraftLimit) {
    super(id, CURRENT, accountNumber, accountHolder, balance, operations, version);
    if (overdraftLimit == null || overdraftLimit.compareTo(BigDecimal.ZERO) < 0) {
      throw new IllegalArgumentException("Overdraft limit must be a non-negative value.");
    }
    this.overdraftLimit = overdraftLimit;
  }


  @Override
  public void withdraw(BigDecimal amount) {
    checkPositiveAmount(amount);
    ensureSufficientFunds(amount);
    operations.add(new BankOperation(amount, WITHDRAWAL));
    balance = balance.subtract(amount);
  }

  private void ensureSufficientFunds(BigDecimal amount) {
    if (balance.add(overdraftLimit).compareTo(amount) < 0) {
      throw new InsufficientFundsException(accountNumber, balance, overdraftLimit, amount);
    }
  }

  @Override
  public void deposit(BigDecimal amount) {
    checkPositiveAmount(amount);
    operations.add(new BankOperation(amount, DEPOSIT));
    balance = balance.add(amount);
  }
}