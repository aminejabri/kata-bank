package io.bank.kata_bank.adapter.api.controller;

import info.schnatterer.mobynamesgenerator.MobyNamesGenerator;
import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;
import io.bank.kata_bank.domain.model.bank_account.BasicAccount;
import io.bank.kata_bank.domain.model.bank_account.CurrentAccount;
import io.bank.kata_bank.domain.model.bank_account.SavingAccount;
import io.bank.kata_bank.domain.service.BankAccountService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to populate the database with random bank accounts. This is intended for testing purposes only. must be disabled in production
 * environments.
 */
@RestController
@RequestMapping("/api/test/accounts/populate")
@AllArgsConstructor
@Validated
public class TestController {

  private final BankAccountService bankAccountService;
  private final MongoTemplate mongoTemplate;

  @PostMapping
  public List<UUID> populate() {
    mongoTemplate.dropCollection(BankAccountDocument.class);
    // Create random accounts and return there IDs
    ArrayList<BankAccount> accounts = new ArrayList<>();
    accounts.add(new CurrentAccount(
        UUID.randomUUID(),
        "FR73" + String.format("%010d", new Random().nextInt(1_000_000_000)),
        MobyNamesGenerator.getRandomName(),
        BigDecimal.ZERO,
        new ArrayList<>(),
        null,
        // random 100 to 1000 with gap of 100
        BigDecimal.valueOf(new Random().nextInt(10) * 100 + 100d)
    ));
    accounts.add(new BasicAccount(
        UUID.randomUUID(),
        "FR73" + String.format("%010d", new Random().nextInt(1_000_000_000)),
        MobyNamesGenerator.getRandomName(),
        BigDecimal.ZERO,
        new ArrayList<>(),
        null
    ));
    accounts.add(new SavingAccount(
        UUID.randomUUID(),
        "FR73" + String.format("%010d", new Random().nextInt(1_000_000_000)),
        MobyNamesGenerator.getRandomName(),
        BigDecimal.ZERO,
        new ArrayList<>(),
        null,
        // random 10000 to 100000 with gap of 1000
        BigDecimal.valueOf(new Random().nextInt(91) * 1000 + 10000d)
    ));
    accounts.forEach(bankAccountService::saveBankAccount);
    return accounts.stream()
        .map(BankAccount::getId)
        .toList();
  }
}
