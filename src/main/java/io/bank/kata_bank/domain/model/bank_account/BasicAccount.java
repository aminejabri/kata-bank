package io.bank.kata_bank.domain.model.bank_account;

import static io.bank.kata_bank.domain.model.bank_account.AccountType.BASIC;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.model.bank_operation.Withdrawal;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;

@DomainEntity
@EqualsAndHashCode(callSuper = true)
public class BasicAccount extends BankAccount {

  public BasicAccount(UUID id, String accountNumber, String accountHolder, BigDecimal balance, List<BankOperation> operations, Long version) {
    super(id, BASIC, accountNumber, accountHolder, balance, operations, version);
  }

  public void withdraw(BigDecimal amount) {
    checkPositiveAmount(amount);
    operations.add(new Withdrawal(amount));
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
    balance = balance.subtract(amount);
  }

  @Override
  public void deposit(BigDecimal amount) {
    checkPositiveAmount(amount);
    balance = balance.add(amount);
  }
}
