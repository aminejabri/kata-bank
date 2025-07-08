package io.bank.kata_bank.adapter.api.dto;

import io.bank.kata_bank.domain.model.AccountType;

public record BankAccountDto(
    String accountNumber,
    AccountType type,
    double balance
) {

}
