package io.bank.kata_bank.adapter.common.mapping;

import io.bank.kata_bank.domain.model.bank_account.Supportable;
import java.util.List;

public class GenericMapperDelegator<E extends Supportable<T>, D extends Supportable<T>, T> implements
    MapperDelegator<E, D, T> {

  private final List<? extends DomainMapper<E, D, T>> mappers;

  public GenericMapperDelegator(List<? extends DomainMapper<E, D, T>> mappers) {
    if (mappers == null) {
      throw new IllegalArgumentException("Mappers list cannot be null or empty");
    }
    for (Object mapper : mappers) {
      if (!(mapper instanceof DomainMapper)) {
        throw new IllegalArgumentException("Mappers list contains non-EntityMapper elements");
      }
    }
    this.mappers = mappers;
  }

  @Override
  public D fromDomain(E entity) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(entity.getType()))
        .findFirst()
        .map(mapper -> mapper.fromDomain(entity))
        .orElseThrow(() -> new IllegalArgumentException(
            "No mapper found for type: " + entity.getType()));
  }


  @Override
  public E toDomain(D other) {
    return mappers.stream()
        .filter(mapper -> mapper.supports(other.getType()))
        .findFirst()
        .map(mapper -> mapper.toDomain(other))
        .orElseThrow(
            () -> new IllegalArgumentException(
                "No mapper found for type: " + other.getType()));
  }
}
