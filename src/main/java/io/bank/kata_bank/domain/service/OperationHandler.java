package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import java.math.BigDecimal;
import java.util.UUID;

public interface OperationHandler {

  boolean supports(BankOperationType type);

  void handle(UUID accountId, BigDecimal amount);
}
