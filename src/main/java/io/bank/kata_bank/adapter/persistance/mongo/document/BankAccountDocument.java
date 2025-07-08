package io.bank.kata_bank.adapter.persistance.mongo.document;

import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_operation.BankOperation;
import java.math.BigDecimal;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "bankAccounts")
@Data
@Builder
public class BankAccountDocument {

  @Indexed(unique = true)
  private String id;
  @Indexed(unique = true)
  private String accountNumber;
  private AccountType accountType;
  private BigDecimal balance;
  private List<BankOperation> operations;
  @Version
  private Integer version;
}