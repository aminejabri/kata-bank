package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.api.mapper.BankAccountMapperFacade;
import io.bank.kata_bank.domain.service.BankAccountService;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {

  private final BankAccountService withdrawService;
  @Qualifier("dtoMapperFacade")
  private final BankAccountMapperFacade bankAccountMapperFacade;

  @GetMapping("/{accountId}")
  public BankAccountDto getAccountDetails(@PathVariable UUID accountId) {
    return bankAccountMapperFacade.fromEntity(withdrawService.getBankAccount(accountId));
  }

  @GetMapping
  public List<BankAccountDto> getAccountDetails() {
    return withdrawService.getAllBankAccounts()
        .stream()
        .map(bankAccountMapperFacade::fromEntity)
        .toList();
  }

//  @PostMapping("/{accountId}/withdraw")
//  public void withdraw(@RequestBody Withdrawal withdrawal) {
//    operationHandler.handleOperation(withdrawal);
//  }
}
