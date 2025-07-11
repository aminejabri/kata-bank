package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.adapter.api.dto.AccountStatementDto;
import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.api.dto.BankOperationDto;
import io.bank.kata_bank.adapter.api.mapper.account.BankAccountMapperDelegator;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import io.bank.kata_bank.domain.service.BankAccountService;
import io.bank.kata_bank.domain.service.OperationHandlerDelegator;
import jakarta.validation.Valid;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
@Validated
public class BankAccountController {

  private final BankAccountService withdrawService;

  @Qualifier("accountDtoMapperDelegator")
  private final BankAccountMapperDelegator bankAccountMapperDelegator;

  private final OperationHandlerDelegator operationHandler;


  @GetMapping("/{accountId}")
  public BankAccountDto getAccountDetails(@PathVariable UUID accountId) {
    return bankAccountMapperDelegator.fromDomain(withdrawService.getBankAccount(accountId));
  }

  @GetMapping("/{accountId}/monthly-statements")
  public AccountStatementDto getMonthlyStatement(@PathVariable UUID accountId) {
    BankAccount bankAccount = withdrawService.getBankAccount(accountId);
    List<BankOperation> bankOperations = bankAccount.monthlyOperations();
    return new AccountStatementDto(
        bankAccount.getAccountNumber(),
        bankAccount.getAccountHolder(),
        bankAccount.getType(),
        bankAccount.getBalance(),
        bankOperations);
  }

  @GetMapping
  public List<BankAccountDto> getAccountDetails() {
    return withdrawService.getAllBankAccounts()
        .stream()
        .map(bankAccountMapperDelegator::fromDomain)
        .toList();
  }

  @PostMapping("/{accountId}/operations")
  @ResponseStatus(HttpStatus.CREATED)
  public void operation(@PathVariable UUID accountId, @Valid @RequestBody BankOperationDto operation) {
    operationHandler.handleOperation(accountId, operation.type(), operation.amount());
  }
}
