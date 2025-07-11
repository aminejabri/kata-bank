package io.bank.kata_bank.domain.common.exception;

public class AccountStateConflictException extends BusinessException {

  public AccountStateConflictException(String accountId) {
    super("The account with id " + accountId + " has been modified concurrently.");
  }
}
