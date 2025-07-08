package io.bank.kata_bank.application.handler.operation;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.BankOperation;
import io.bank.kata_bank.domain.model.BankOperationType;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import io.bank.kata_bank.domain.service.OperationHandler;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class WithdrawalHandler implements OperationHandler {

  private final BankAccountRepository bankAccountRepository;

  @Override
  public boolean supports(BankOperationType type) {
    return BankOperationType.WITHDRAWAL.equals(type);
  }

  @Override
  public void handle(BankOperation operation) throws InsufficientFundsException {
    Long accountId = operation.getBankAccount().getId();
    bankAccountRepository.getAccountById(accountId)
        .ifPresentOrElse(
            account -> {
              log.debug("Withdrawing {} from account with id {}", operation, accountId);
              account.withdraw(operation);
              bankAccountRepository.save(account);
              log.debug("Withdrawal of {} from account with id {} completed successfully", operation, accountId);
            },
            () -> {
              throw new EntityNotFoundException("Account with id " + accountId + " not found");
            });
  }
}
