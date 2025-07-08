package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;

public interface OperationHandler {

  boolean supports(BankOperationType type);

  void handle(BankOperation operation);
}
