package io.bank.kata_bank.domain.model.bank_account;

import static lombok.AccessLevel.PRIVATE;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Builder
@AllArgsConstructor(access = PRIVATE)
@RequiredArgsConstructor(access = PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DomainEntity
public class CurrentAccount implements BankAccount {

  @Include
  private UUID id = UUID.randomUUID();

  private final AccountType type = AccountType.CURRENT;

  private final String accountNumber;

  private final Client client;

  private BigDecimal overdraftLimit;

  private BigDecimal balance;

  private final List<BankOperation> operations = new ArrayList<>();


  @Override
  public void withdraw(BigDecimal amount) {
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
    balance = balance.subtract(amount);
  }

  private void ensureSufficientFunds(BigDecimal amount) {
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
  }

  @Override
  public void deposit(BigDecimal amount) {

  }
}