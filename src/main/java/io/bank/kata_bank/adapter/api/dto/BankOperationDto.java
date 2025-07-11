package io.bank.kata_bank.adapter.api.dto;

import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import io.bank.kata_bank.domain.common.mappers.Supportable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

@Valid
public record BankOperationDto(@NotNull BigDecimal amount, @NotNull BankOperationType type) implements Supportable<BankOperationType> {

  @Override
  public BankOperationType getType() {
    return type;
  }
}
