package io.bank.kata_bank.adapter.api.mapper;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.model.BankAccount;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BankAccountDtoMapperFacade implements BankAccountMapperFacade {

  private final List<? extends BankAccountDtoMapper<? extends BankAccount>> mappers;

  public <T extends BankAccount> BankAccountDto toDto(T bankAccount) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(bankAccount.getType()))
        .findFirst()
        .map(mapper -> this.castAndMap(mapper, bankAccount))
        .orElseThrow(() -> new IllegalArgumentException("No mapper found for account type: " + bankAccount.getType()));
  }

  @SuppressWarnings("unchecked")
  private <T extends BankAccount> BankAccountDto castAndMap(BankAccountDtoMapper<?> rawMapper, T account) {
    BankAccountDtoMapper<T> mapper = (BankAccountDtoMapper<T>) rawMapper;
    return mapper.toDto(account);
  }

  @Override
  public BankAccount toEntity(BankAccountDto dto) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(dto.type()))
        .findFirst()
        .map(mapper -> mapper.toEntity(dto))
        .orElseThrow(() -> new IllegalArgumentException("No mapper found for account type: " + dto.type()));
  }
}
