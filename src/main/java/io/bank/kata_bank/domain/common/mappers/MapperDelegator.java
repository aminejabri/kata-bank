package io.bank.kata_bank.domain.common.mappers;

public interface MapperDelegator<E extends Supportable<T>, D extends Supportable<T>, T> {

  D fromDomain(E entity);

  E toDomain(D other);
}