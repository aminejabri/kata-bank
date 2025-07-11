package io.bank.kata_bank.domain.common.mappers;

public interface DomainMapper<E, D, T> {

  boolean supports(T type);

  D fromDomain(E entity);

  E toDomain(D other);
}
