package io.bank.kata_bank.adapter.common.mapping;

public interface DomainMapper<E, D, T> {

  boolean supports(T type);

  D fromDomain(E entity);

  E toDomain(D other);
}
