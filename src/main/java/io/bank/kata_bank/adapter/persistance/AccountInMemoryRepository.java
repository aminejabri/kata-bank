package io.bank.kata_bank.adapter.persistance;

import io.bank.kata_bank.domain.model.Account;
import io.bank.kata_bank.domain.port.repository.AccountRepository;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;

@Repository
public class AccountInMemoryRepository implements AccountRepository, InitializingBean {

  private final Map<Long, Account> accounts = new ConcurrentHashMap<>();

  @Override
  public Optional<Account> getAccountById(Long id) {
    return Optional.ofNullable(accounts.get(id));
  }

  @Override
  public void save(Account account) {
    accounts.put(account.getId(), account);
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    accounts.put(1L,
        Account.builder()
            .id(1L)
            .accountNumber("123456789")
            .client(null)
            .balance(BigDecimal.valueOf(1000))
            .withdrawals(new ArrayList<>())
            .build());
  }
}
