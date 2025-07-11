package io.bank.kata_bank.domain.model.bank_account;

import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.common.mappers.Supportable;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
public abstract class BankAccount implements Supportable<AccountType> {

  @Include
  protected UUID id;

  protected final AccountType type;

  protected final String accountNumber;

  protected final String accountHolder;

  protected BigDecimal balance;

  protected Long version;

  protected final List<BankOperation> operations;

  protected BankAccount(UUID id, AccountType type, String accountNumber, String accountHolder, BigDecimal balance, List<BankOperation> operations,
      Long version) {
    this.id = id;
    this.type = type;
    this.accountNumber = accountNumber;
    this.accountHolder = accountHolder;
    this.balance = balance;
    this.operations = operations == null ? new ArrayList<>() : operations;
    this.version = version;
  }

  public List<BankOperation> monthlyOperations() {
    // zone id must be set to user's local time zone in real applications
    return monthlyOperations(LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault()));
  }

  public List<BankOperation> monthlyOperations(LocalDate localDate) {
    return operations.stream()
        .filter(operation -> operation.timestamp().atZone(ZoneId.systemDefault()).toLocalDate()
            .isAfter(localDate.minusDays(30)))
        .sorted(Comparator.comparing(BankOperation::timestamp).reversed())
        .toList();
  }

  public abstract void withdraw(BigDecimal amount);

  public abstract void deposit(BigDecimal amount);

  protected void checkPositiveAmount(BigDecimal amount) {
    if (amount.signum() <= 0) {
      throw new InvalidBankOperationException("Operation amount must be positive");
    }
  }
}
