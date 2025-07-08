package io.bank.kata_bank.application.handler.operation;

import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.service.OperationHandler;
import io.bank.kata_bank.domain.service.OperationHandlerFacade;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class DefaultOperationHandlerFacade implements OperationHandlerFacade {

  private final List<OperationHandler> operationHandlers;

  @Override
  public void handleOperation(BankOperation operation) {
    operationHandlers.stream()
        .filter(handler -> handler.supports(operation.getType()))
        .findFirst()
        .ifPresentOrElse(
            handler -> handler.handle(operation),
            () -> log.warn("No handler found for operation type: {}", operation.getType())
        );
  }
}
