package io.bank.kata_bank.adapter.persistance;

import io.bank.kata_bank.domain.model.BankAccount;
import io.bank.kata_bank.domain.model.BasicAccount;
import io.bank.kata_bank.domain.model.CurrentAccount;
import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

@Repository
public class BankAccountInMemoryRepository implements BankAccountRepository, InitializingBean {

  private final Map<Long, BankAccount> accounts = new ConcurrentHashMap<>();

  @Override
  public Optional<BankAccount> getAccountById(Long id) {
    return Optional.ofNullable(accounts.get(id));
  }

  @Override
  public void save(BankAccount account) {
    accounts.put(account.getId(), account);
  }

  @Override
  public void afterPropertiesSet() {
    accounts.put(1L, CurrentAccount.builder()
        .id(1L)
        .balance(BigDecimal.ONE)
        .overdraftLimit(BigDecimal.valueOf(400))
        .build());
    accounts.put(2L, BasicAccount.builder()
        .id(2L)
        .balance(BigDecimal.ZERO)
        .build());
  }
}
