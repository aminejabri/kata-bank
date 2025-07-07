package io.bank.kata_bank.domain.common.exception;

import java.io.Serial;

public class BusinessException extends RuntimeException {

  @Serial
  private static final long serialVersionUID = 7030194525010312451L;

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(String message, Throwable cause) {
    super(message, cause);
  }

  public BusinessException(Throwable cause) {
    super(cause);
  }
}
