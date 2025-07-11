package io.bank.kata_bank.adapter.persistance.jpa.repository;

import io.bank.kata_bank.adapter.persistance.jpa.model.BankAccountEntity;
import java.util.UUID;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@ConditionalOnProperty(name = "kata.bank.adapter.persistance.jpa.enabled", havingValue = "true")
public interface BankAccountJpaRepository extends JpaRepository<BankAccountEntity, UUID> {

}
