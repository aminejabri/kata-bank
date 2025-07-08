package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.adapter.api.mapper.BankAccountMapperFacade;
import io.bank.kata_bank.domain.service.BankAccountService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class BankAccountController {

  private final BankAccountService withdrawService;
  private final BankAccountMapperFacade bankAccountMapperFacade;

  @GetMapping("/{accountId}")
  public BankAccountDto getAccountDetails(@PathVariable Long accountId) {
    return bankAccountMapperFacade.toDto(withdrawService.getBankAccount(accountId));
  }
}
