package io.bank.kata_bank.application.handler.operation;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import io.bank.kata_bank.domain.service.OperationHandler;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class DepositHandler implements OperationHandler {

  private final BankAccountRepository bankAccountRepository;

  @Override
  public boolean supports(BankOperationType type) {
    return BankOperationType.DEPOSIT.equals(type);
  }

  @Override
  public void handle(BankOperation operation) throws InsufficientFundsException {
    UUID accountId = operation.getBankAccount().getId();
    bankAccountRepository.getAccountById(accountId)
        .ifPresentOrElse(
            account -> {
              log.debug("Depositing {} into account with id {}", operation, accountId);
              account.deposit(operation.getAmount());
              bankAccountRepository.save(account);
              log.debug("Deposit of {} into account with id {} completed successfully", operation,
                  accountId);
            },
            () -> {
              throw new EntityNotFoundException("Account with id " + accountId + " not found");
            });
  }
}
