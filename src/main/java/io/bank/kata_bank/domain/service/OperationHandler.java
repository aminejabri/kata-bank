package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.BankOperation;
import io.bank.kata_bank.domain.model.BankOperationType;

public interface OperationHandler {

  boolean supports(BankOperationType type);

  void handle(BankOperation operation);
}
