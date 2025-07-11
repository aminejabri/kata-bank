package io.bank.kata_bank.adapter.api.mapper.account;

import io.bank.kata_bank.adapter.api.dto.BankAccountDto;
import io.bank.kata_bank.domain.common.mappers.MapperDelegator;
import io.bank.kata_bank.domain.model.bank_account.AccountType;
import io.bank.kata_bank.domain.model.bank_account.BankAccount;

public interface BankAccountMapperDelegator extends
    MapperDelegator<BankAccount, BankAccountDto, AccountType> {

}