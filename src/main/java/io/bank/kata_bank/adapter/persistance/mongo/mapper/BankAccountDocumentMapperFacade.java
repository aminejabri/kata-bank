package io.bank.kata_bank.adapter.persistance.mongo.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountDocumentMapperFacade implements BankAccountMapperFacade {

  private final List<? extends BankAccountDocumentMapper<? extends BankAccount>> mappers;

  public <T extends BankAccount> BankAccountDto fromEntity(T bankAccount) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(bankAccount.getType()))
        .findFirst()
        .map(mapper -> this.castAndMap(mapper, bankAccount))
        .orElseThrow(() -> new IllegalArgumentException(
            "No mapper found for account type: " + bankAccount.getType()));
  }

  @SuppressWarnings("unchecked")
  private <T extends BankAccount> BankAccountDto castAndMap(BankAccountDocumentMapper<?> rawMapper,
      T account) {
    BankAccountDocumentMapper<T> mapper = (BankAccountDocumentMapper<T>) rawMapper;
    return mapper.toDto(account);
  }

  @Override
  public BankAccount toEntity(BankAccountDto dto) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(dto.type()))
        .findFirst()
        .map(mapper -> mapper.toEntity(dto))
        .orElseThrow(
            () -> new IllegalArgumentException("No mapper found for account type: " + dto.type()));
  }
}
