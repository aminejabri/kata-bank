package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.domain.model.Withdrawal;
import io.bank.kata_bank.domain.service.WithdrawService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/accounts")
@AllArgsConstructor
public class AccountController {

  private final WithdrawService withdrawService;

  @PostMapping("/{accountId}/withdraw")
  public void withdraw(@RequestBody Withdrawal withdrawal) {
    withdrawService.withdraw(withdrawal);
  }
}
