package org.example.entrypoints.controllers.advices;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@ConditionalOnClass(PersistenceException.class)
@RequiredArgsConstructor
public class DatabaseExceptionHandlerAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleFlkExceptions(EntityNotFoundException e) {
    log.warn("EntityNotFoundException occurred with the message: {}", e.getMessage());
    return ResponseEntity.notFound().build();
  }
}
