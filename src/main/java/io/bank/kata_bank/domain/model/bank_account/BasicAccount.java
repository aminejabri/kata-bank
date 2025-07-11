package io.bank.kata_bank.domain.model.bank_account;

import static io.bank.kata_bank.domain.model.bank_account.AccountType.BASIC;
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

@DomainEntity
@EqualsAndHashCode(callSuper = true)
@AggregateRoot
public class BasicAccount extends BankAccount {

  public BasicAccount(UUID id, String accountNumber, String accountHolder, BigDecimal balance, List<BankOperation> operations, Long version) {
    super(id, BASIC, accountNumber, accountHolder, balance, operations, version);
  }

  public void withdraw(BigDecimal amount) {
    checkPositiveAmount(amount);
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
    operations.add(new BankOperation(amount, WITHDRAWAL));
    balance = balance.subtract(amount);
  }

  @Override
  public void deposit(BigDecimal amount) {
    checkPositiveAmount(amount);
    operations.add(new BankOperation(amount, DEPOSIT));
    balance = balance.add(amount);
  }
}
