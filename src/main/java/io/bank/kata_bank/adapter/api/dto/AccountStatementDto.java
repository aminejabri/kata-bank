package io.bank.kata_bank.adapter.api.dto;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.List;

public record AccountStatementDto(
    String accountNumber,
    String accountHolder,
    AccountType type,
    BigDecimal balance,
    List<BankOperation> operations
) {

}
