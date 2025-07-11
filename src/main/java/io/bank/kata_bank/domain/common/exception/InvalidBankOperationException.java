package io.bank.kata_bank.domain.common.exception;

public class InvalidBankOperationException extends RuntimeException {

  public InvalidBankOperationException(String cause) {
    super("Invalid operation: " + cause);
  }
}
