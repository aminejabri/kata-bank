package io.bank.kata_bank.adapter.persistance.jpa.model;

import com.mongodb.client.model.changestream.OperationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "bank_operation")
@AllArgsConstructor
@NoArgsConstructor
public class BankOperationEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  @Column(name = "operation_id", nullable = false, unique = true)
  private UUID id;

  @ManyToOne(optional = false,
      targetEntity = BankAccountEntity.class,
      fetch = FetchType.EAGER
  )
  private BankAccountEntity bankAccount;

  @Column(name = "operation_amount", nullable = false)
  private BigDecimal amount;

  @Column(name = "operation_type", nullable = false)
  private OperationType operationType;
}
