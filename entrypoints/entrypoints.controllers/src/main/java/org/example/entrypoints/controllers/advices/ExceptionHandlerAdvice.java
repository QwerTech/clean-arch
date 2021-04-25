package org.example.entrypoints.controllers.advices;

import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionHandlerAdvice {

  @ExceptionHandler(EntityNotFoundException.class)
  public ResponseEntity<?> handleFlkExceptions(EntityNotFoundException e) {
    log.warn("EntityNotFoundException occurred with the message: {}", e.getMessage());
    return ResponseEntity.notFound().build();
  }
}
