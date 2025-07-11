package io.bank.kata_bank.adapter.api.controller;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;

import io.bank.kata_bank.domain.common.exception.AccountStateConflictException;
import io.bank.kata_bank.domain.common.exception.EntityNotFoundException;
import io.bank.kata_bank.domain.common.exception.InsufficientFundsException;
import io.bank.kata_bank.domain.common.exception.InvalidBankOperationException;
import io.bank.kata_bank.domain.common.exception.UnsupportedBankOperation;
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
  public ResponseEntity<ProblemDetail> handleInvalidOperation(InvalidBankOperationException ex) {
    return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(ex, BAD_REQUEST));
  }

  @ExceptionHandler(AccountStateConflictException.class)
  public ResponseEntity<ProblemDetail> handleAccountStateConflict(AccountStateConflictException ex) {
    return ResponseEntity.status(CONFLICT).body(buildProblemDetail(ex, CONFLICT));
  }

  @ExceptionHandler(UnsupportedBankOperation.class)
  public ResponseEntity<ProblemDetail> handleUnsupportedOperation(UnsupportedBankOperation ex) {
    return ResponseEntity.status(BAD_REQUEST).body(buildProblemDetail(ex, BAD_REQUEST));
  }


  private static ProblemDetail buildProblemDetail(Exception ex, HttpStatus status) {
    return ProblemDetail.forStatusAndDetail(status, ex.getMessage());
  }
}
