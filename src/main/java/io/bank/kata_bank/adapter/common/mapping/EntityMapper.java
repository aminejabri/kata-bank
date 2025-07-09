package io.bank.kata_bank.adapter.common.mapping;

public interface EntityMapper<E, D, T> {

  boolean supports(T type);

  D fromEntity(E entity);

  E toEntity(D other);
}
