package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.api.mapper.BankAccountMapperFacade;
import io.bank.kata_bank.domain.model.bank_operation.Withdrawal;
import io.bank.kata_bank.domain.service.BankAccountService;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {

  private final BankAccountService withdrawService;
  private final BankAccountMapperFacade bankAccountMapperFacade;

  @GetMapping("/{accountId}")
  public BankAccountDto getAccountDetails(@PathVariable UUID accountId) {
    return bankAccountMapperFacade.fromEntity(withdrawService.getBankAccount(accountId));
  }

  @PostMapping("/{accountId}/withdraw")
  public void withdraw(@RequestBody Withdrawal withdrawal) {
    operationHandler.handleOperation(withdrawal);
  }
}
