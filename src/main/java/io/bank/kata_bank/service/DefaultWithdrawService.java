package io.bank.kata_bank.service;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.Withdrawal;
import io.bank.kata_bank.domain.port.repository.AccountRepository;
import io.bank.kata_bank.domain.service.WithdrawService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
@AllArgsConstructor
@Slf4j
public class DefaultWithdrawService implements WithdrawService {

  private final AccountRepository accountRepository;

  @Override
  public void withdraw(Withdrawal withdrawal) throws InsufficientFundsException {
    Long accountId = withdrawal.accountId();
    accountRepository.getAccountById(accountId)
        .ifPresentOrElse(
            account -> {
              log.debug("Withdrawing {} from account with id {}", withdrawal, accountId);
              account.withdraw(withdrawal);
              accountRepository.save(account);
              log.debug("Withdrawal of {} from account with id {} completed successfully", withdrawal, accountId);
            },
            () -> {
              throw new EntityNotFoundException("Account with id " + accountId + " not found");
            });
  }
}
