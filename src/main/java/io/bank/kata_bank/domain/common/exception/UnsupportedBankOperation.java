package io.bank.kata_bank.domain.common.exception;

import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import java.io.Serial;

public class UnsupportedBankOperation extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 9062888737160134136L;

  public UnsupportedBankOperation(BankOperationType operationType) {
    super("Unsupported bank operation: " + operationType);
  }
}
