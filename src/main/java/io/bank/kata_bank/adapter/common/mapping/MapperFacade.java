package io.bank.kata_bank.adapter.common.mapping;

import io.bank.kata_bank.domain.model.bank_account.Supportable;

public interface MapperFacade<E extends Supportable<T>, D extends Supportable<T>, T> {

  D fromEntity(E entity);

  E toEntity(D other);
}