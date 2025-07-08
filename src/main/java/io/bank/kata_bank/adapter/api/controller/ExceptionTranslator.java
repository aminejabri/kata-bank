package io.bank.kata_bank.adapter.api.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionTranslator {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<ProblemDetail> handleEntityNotFound(EntityNotFoundException ex) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(buildProblemDetail(ex, HttpStatus.NOT_FOUND));
  }

  @ExceptionHandler(InsufficientFundsException.class)
  public ResponseEntity<ProblemDetail> handleInsufficientFunds(InsufficientFundsException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(ex, BAD_REQUEST));
  }

  @ExceptionHandler(InvalidBankOperationException.class)
  public ResponseEntity<ProblemDetail> handleInvalidWithdrawal(InvalidBankOperationException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(ex, BAD_REQUEST));
  }

  private static ProblemDetail buildProblemDetail(Exception ex, HttpStatus status) {
    return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
  }
}
