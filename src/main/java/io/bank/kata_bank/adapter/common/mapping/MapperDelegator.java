package io.bank.kata_bank.adapter.common.mapping;

import io.bank.kata_bank.domain.model.bank_account.Supportable;

public interface MapperDelegator<E extends Supportable<T>, D extends Supportable<T>, T> {

  D fromDomain(E entity);

  E toDomain(D other);
}