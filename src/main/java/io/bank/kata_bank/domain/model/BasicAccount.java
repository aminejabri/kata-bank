package io.bank.kata_bank.domain.model;

import static lombok.AccessLevel.PRIVATE;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
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
  private Long id;

  private final AccountType type = AccountType.BASIC;

  private final String accountNumber;

  private final Client client;

  private BigDecimal balance;

  private final List<Withdrawal> withdrawals = new ArrayList<>();

  public void withdraw(Withdrawal withdrawal) {
    ensureSufficientFunds(withdrawal.amount());
    performWithdrawal(withdrawal);
  }

  private void ensureSufficientFunds(BigDecimal amount) {
    if (amount.compareTo(balance) > 0) {
      throw new InsufficientFundsException(accountNumber, balance, amount);
    }
  }

  private void performWithdrawal(Withdrawal withdrawal) {
    balance = balance.subtract(withdrawal.amount());
    withdrawals.add(withdrawal);
  }
}
