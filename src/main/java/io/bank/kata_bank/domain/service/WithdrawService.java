package io.bank.kata_bank.domain.service;

import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.model.Withdrawal;

public interface WithdrawService {

  void withdraw(Withdrawal withdrawal) throws InsufficientFundsException;
}
