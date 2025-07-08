package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.BankOperation;

public interface OperationHandlerFacade {

  void handleOperation(BankOperation operation);
}
