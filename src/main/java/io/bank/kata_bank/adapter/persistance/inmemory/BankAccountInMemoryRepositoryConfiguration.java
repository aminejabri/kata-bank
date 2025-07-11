//package io.bank.kata_bank.adapter.persistance.inmemory;
//
//import io.bank.kata_bank.domain.port.repository.BankAccountRepository;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class BankAccountInMemoryRepositoryConfiguration {
//
//  @Bean
//  @ConditionalOnMissingBean(BankAccountRepository.class)
//  BankAccountRepository bankAccountRepository() {
//    return new BankAccountInMemoryRepository();
//  }
//}
