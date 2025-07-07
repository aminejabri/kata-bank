package io.bank.kata_bank.domain.common.exception;

import java.math.BigDecimal;

public class InsufficientFundsException extends RuntimeException {

  public InsufficientFundsException(String accountNumber, BigDecimal currentBalance, BigDecimal withdrawalAmount) {
    super(String.format("Insufficient funds in account %s current balance is %s, but withdrawal amount is %s",
        accountNumber, currentBalance, withdrawalAmount));
  }
}
