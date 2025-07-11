package io.bank.kata_bank.adapter.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import io.bank.kata_bank.adapter.persistance.mongo.document.BankAccountDocument;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_operation.BankOperationType;
import java.math.BigDecimal;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class BankAccountControllerIT {

  @Autowired
  MongoTemplate mongoTemplate;

  @Autowired
  private MockMvc mockMvc;

  @Test
  void shouldWithdrawFromAccount() throws Exception {
    UUID accountId = UUID.randomUUID();
    mongoTemplate.save(BankAccountDocument.builder()
        .id(accountId.toString())
        .accountHolder("John Doe")
        .accountNumber("123456789")
        .type(AccountType.BASIC)
        .balance(BigDecimal.valueOf(1000))
        .build());

    mockMvc.perform(post("/api/accounts/{id}/operations", accountId)
            .content("{\"amount\": 100, \"type\": \"WITHDRAWAL\"}")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

    BankAccountDocument document = mongoTemplate.findById(accountId.toString(), BankAccountDocument.class);
    assertThat(document).isNotNull();
    assertThat(document.getBalance()).isEqualByComparingTo(BigDecimal.valueOf(900));
    assertThat(document.getOperations()).hasSize(1);
    assertThat(document.getOperations().getFirst().amount()).isEqualByComparingTo(BigDecimal.valueOf(100));
    assertThat(document.getOperations().getFirst().type()).isEqualTo(BankOperationType.WITHDRAWAL);
  }
}