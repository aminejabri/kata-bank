package io.bank.kata_bank.domain.model.bank_account;

import static io.bank.kata_bank.domain.model.bank_account.AccountType.SAVING;
import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.DEPOSIT;
import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.WITHDRAWAL;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@DomainEntity
@EqualsAndHashCode(callSuper = true)
public class SavingAccount extends BankAccount {

  private final BigDecimal savingsCap;

  public SavingAccount(UUID id,
      String accountNumber,
      String accountHolder,
      BigDecimal balance,
      List<BankOperation> operations,
      Long version,
      BigDecimal savingsCap) {
    super(id, SAVING, accountNumber, accountHolder, balance, operations, version);
    if (savingsCap.signum() <= 0) {
      throw new IllegalArgumentException("Operation amount must be positive");
    }
    this.savingsCap = savingsCap;
  }


  @Override
  public void withdraw(BigDecimal amount) {
    checkPositiveAmount(amount);
    ensureSufficientFunds(amount);
    operations.add(new BankOperation(amount, WITHDRAWAL));
    balance = balance.subtract(amount);
  }

  private void ensureSufficientFunds(BigDecimal amount) {
    if (balance.add(savingsCap).compareTo(amount) < 0) {
      throw new InsufficientFundsException(accountNumber, balance, savingsCap, amount);
    }
  }

  @Override
  public void deposit(BigDecimal amount) {
    checkPositiveAmount(amount);
    if (balance.add(amount).compareTo(savingsCap) > 0) {
      throw new InvalidBankOperationException("Deposit exceeds savings cap");
    }
    operations.add(new BankOperation(amount, DEPOSIT));
    balance = balance.add(amount);
  }
}