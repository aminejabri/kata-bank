package io.bank.kata_bank.adapter.api.controller;

import io.bank.kata_bank.domain.model.Withdrawal;
import io.bank.kata_bank.domain.service.OperationHandlerFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/operations")
@AllArgsConstructor
public class OperationController {

  private final OperationHandlerFacade operationHandler;

  @PostMapping("/{accountId}/withdraw")
  public void withdraw(@RequestBody Withdrawal withdrawal) {
    operationHandler.handleOperation(withdrawal);
  }
}
