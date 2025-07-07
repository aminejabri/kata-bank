package io.bank.kata_bank.domain.common.exception;

public class InvalidWithdrawalException extends RuntimeException {

  public InvalidWithdrawalException(String cause) {
    super("Invalid withdrawal operation: " + cause);
  }
}
