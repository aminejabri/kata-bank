package io.bank.kata_bank.adapter.persistance.mongo.configuration;

import org.springframework.boot.autoconfigure.condition.AllNestedConditions;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;

public class MongoRepositoryCondition extends AllNestedConditions {

  public MongoRepositoryCondition() {
    super(ConfigurationPhase.REGISTER_BEAN);
  }

  @ConditionalOnBooleanProperty(prefix = "kata.bank.adapter.persistance.mongo", name = "enabled")
  static class OnEnabled {

  }
}
