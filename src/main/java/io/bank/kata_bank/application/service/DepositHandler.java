package io.bank.kata_bank.application.service;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import io.bank.kata_bank.domain.service.OperationHandler;
import java.math.BigDecimal;
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
  public void handle(UUID accountId, BigDecimal amount) throws InsufficientFundsException {
    bankAccountRepository.getAccountById(accountId)
        .ifPresentOrElse(
            account -> {
              log.debug("Depositing {} into account with id {}", amount, accountId);
              account.deposit(amount);
              bankAccountRepository.save(account);
              log.debug("Deposit of {} into account with id {} completed successfully", amount,
                  accountId);
            },
            () -> {
              throw new EntityNotFoundException("Account with id " + accountId + " not found");
            });
  }
}
