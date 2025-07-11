package io.bank.kata_bank.domain.model.bank_account;

import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.DEPOSIT;
import static io.bank.kata_bank.domain.model.bank_operation.BankOperationType.WITHDRAWAL;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BasicAccountTest {

  @Test
  void deposit_shouldIncreaseBalanceAndAddWithdrawal() {
    BasicAccount account = buildAccount(BigDecimal.ZERO);

    account.deposit(BigDecimal.TEN);

    assertThat(account.getBalance()).isEqualTo(BigDecimal.TEN);
    assertThat(account.getOperations()).hasSize(1);
    assertThat(account.getOperations().getFirst().amount()).isEqualTo(BigDecimal.TEN);
    assertThat(account.getOperations().getFirst().type()).isEqualTo(DEPOSIT);
  }

  @Test
  void withdraw_sufficientBalance_shouldDecreaseBalanceAndAddWithdrawal() {
    BasicAccount account = buildAccount(BigDecimal.TEN);

    account.withdraw(BigDecimal.ONE);

    assertThat(account.getBalance()).isEqualTo(BigDecimal.valueOf(9));
    assertThat(account.getOperations()).hasSize(1);
    assertThat(account.getOperations().getFirst().amount()).isEqualTo(BigDecimal.ONE);
    assertThat(account.getOperations().getFirst().type()).isEqualTo(WITHDRAWAL);
  }

  @Test
  void withdraw_insufficientBalance_shouldThrowException() {
    BasicAccount account = buildAccount(BigDecimal.ONE);

    assertThatThrownBy(() -> account.withdraw(BigDecimal.TEN))
        .isInstanceOf(InsufficientFundsException.class)
        .hasMessage("Insufficient funds in account Test Account current balance is 1, but withdrawal amount is 10");

    assertThat(account.getBalance()).isEqualTo(BigDecimal.ONE);
    assertThat(account.getOperations()).isEmpty();
  }

  @Test
  void monthlyOperations_shouldReturnOperationsForLast3ODays() {
    BasicAccount account = buildAccountWithOperations(Instant.now());

    List<BankOperation> result = account.monthlyOperations();

    assertThat(result).hasSize(2);
    assertThat(result.getFirst().amount()).isEqualTo(BigDecimal.valueOf(1));
    assertThat(result.getFirst().type()).isEqualTo(DEPOSIT);
    assertThat(result.getLast().amount()).isEqualTo(BigDecimal.valueOf(29));
    assertThat(result.getLast().type()).isEqualTo(WITHDRAWAL);
  }


  private BasicAccount buildAccountWithOperations(Instant referenceDate) {
    ArrayList<BankOperation> operations = new ArrayList<>();

    operations.add(new BankOperation(referenceDate, BigDecimal.valueOf(1), DEPOSIT)); // today
    operations.add(new BankOperation(referenceDate.minus(31, ChronoUnit.DAYS), BigDecimal.valueOf(31), DEPOSIT)); // too old
    operations.add(new BankOperation(referenceDate.minus(29, ChronoUnit.DAYS), BigDecimal.valueOf(29), WITHDRAWAL)); // valid

    return new BasicAccount(UUID.randomUUID(), "Test Account", "holder", BigDecimal.TEN, operations, 0L);
  }

  private static BasicAccount buildAccount(BigDecimal balance) {
    return new BasicAccount(UUID.randomUUID(), "Test Account", "holder", balance, new ArrayList<>(), 0L);
  }
}