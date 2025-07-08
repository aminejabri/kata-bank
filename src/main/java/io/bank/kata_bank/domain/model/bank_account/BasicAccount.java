package io.bank.kata_bank.domain.model.bank_account;

import static lombok.AccessLevel.PRIVATE;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.model.bank_operation.Withdrawal;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;

@Builder
@AllArgsConstructor(access = PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DomainEntity
public class BasicAccount implements BankAccount {

  @Include
  private UUID id;

  private final AccountType type = AccountType.BASIC;

  private final String accountNumber;

  private final Client client;

  private BigDecimal balance;

  private final List<BankOperation> operations = new ArrayList<>();

  public void withdraw(BigDecimal amount) {
    operations.add(new Withdrawal(this, amount));
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
    balance = balance.subtract(amount);
  }

  @Override
  public void deposit(BigDecimal amount) {
    if (amount.compareTo(BigDecimal.ZERO) <= 0) {
      throw new InvalidBankOperationException("Deposit amount must be positive");
    }
    balance = balance.add(amount);
  }
}
