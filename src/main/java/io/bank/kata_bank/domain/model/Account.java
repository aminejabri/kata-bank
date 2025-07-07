package io.bank.kata_bank.domain.model;

import static lombok.AccessLevel.PRIVATE;

import io.bank.kata_bank.domain.common.annotation.DDD.DomainEntity;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@DomainEntity
public class Account {

  @Include
  private Long id;

  private String accountNumber;

  private Client client;

  private BigDecimal balance;

  private List<Withdrawal> withdrawals;

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
