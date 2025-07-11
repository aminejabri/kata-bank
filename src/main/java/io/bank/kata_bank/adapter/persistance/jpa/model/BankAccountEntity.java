package io.bank.kata_bank.adapter.persistance.jpa.model;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "bank_account")
@Builder
public class BankAccountEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "account_id", updatable = false, nullable = false)
  private UUID id;

  @Column(name = "account_number", nullable = false, unique = true)
  private String accountNumber;

  @Column(name = "account_holder_name", nullable = false)
  private String accountHolderName;

  @Column(name = "balance", nullable = false)
  private BigDecimal balance;

  @Column(name = "account_type", nullable = false)
  private AccountType accountType;

  @OneToMany(mappedBy = "bankAccount")
  private List<BankOperationEntity> operations;
}
