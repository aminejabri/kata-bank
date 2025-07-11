package io.bank.kata_bank.application.handler.operation;

import io.bank.kata_bank.domain.common.exception.UnsupportedBankOperation;
import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import io.bank.kata_bank.domain.service.OperationHandler;
import io.bank.kata_bank.domain.service.OperationHandlerDelegator;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class DefaultOperationHandlerDelegator implements OperationHandlerDelegator {

  private final List<OperationHandler> operationHandlers;

  @Override
  public void handleOperation(UUID accountId, BankOperationType type, BigDecimal amount) {
    operationHandlers.stream()
        .filter(handler -> handler.supports(type))
        .findFirst()
        .ifPresentOrElse(
            handler -> handler.handle(accountId, amount),
            () -> {
              throw new UnsupportedBankOperation(type);
            }
        );
  }
}
