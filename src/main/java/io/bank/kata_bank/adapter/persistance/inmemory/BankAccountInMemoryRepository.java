//package io.bank.kata_bank.adapter.persistance.inmemory;
//
//import io.bank.kata_bank.domain.model.bank_account.BankAccount;
//import io.bank.kata_bank.domain.model.bank_account.BasicAccount;
//import io.bank.kata_bank.domain.model.bank_account.CurrentAccount;
//import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
//import java.math.BigDecimal;
//import java.util.List;
//import java.util.Map;
//import java.util.Optional;
//import java.util.UUID;
//import java.util.concurrent.ConcurrentHashMap;
//import org.springframework.beans.factory.InitializingBean;
//
//public class BankAccountInMemoryRepository implements BankAccountRepository, InitializingBean {
//
//  private final Map<UUID, BankAccount> accounts = new ConcurrentHashMap<>();
//
//  @Override
//  public Optional<BankAccount> getAccountById(UUID id) {
//    return Optional.ofNullable(accounts.get(id));
//  }
//
//  @Override
//  public List<BankAccount> getAll() {
//    return List.copyOf(accounts.values());
//  }
//
//  @Override
//  public void save(BankAccount account) {
//    accounts.put(account.getId(), account);
//  }
//
//  @Override
//  public void afterPropertiesSet() {
//    UUID firstAccountId = UUID.randomUUID();
//    accounts.put(firstAccountId, CurrentAccount.builder()
//        .id(firstAccountId)
//        .balance(BigDecimal.ONE)
//        .overdraftLimit(BigDecimal.valueOf(400))
//        .build());
//
//    UUID secondAccountId = UUID.randomUUID();
//    accounts.put(secondAccountId, BasicAccount.builder()
//        .id(secondAccountId)
//        .balance(BigDecimal.ZERO)
//        .build());
//  }
//}
